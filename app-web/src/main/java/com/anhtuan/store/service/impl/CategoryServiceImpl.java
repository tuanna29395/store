package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.commons.enums.StatusType;
import com.anhtuan.store.dto.request.CategoryAddDto;
import com.anhtuan.store.dto.request.CategorySearchDto;
import com.anhtuan.store.dto.response.CategoryResponseDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.CategoryEntity;
import com.anhtuan.store.model.QCategoryEntity;
import com.anhtuan.store.repository.CategoryRepository;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.service.CategoryService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<CategoryResponseDto> getAll(CategorySearchDto searchDto) {
        BooleanBuilder condition = new BooleanBuilder();
        QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;
        if (searchDto.getStatus().equals(StatusType.NOT_DELETE.getVal())) {
            condition.and(categoryEntity.status.ne(StatusType.DELETED.getVal()));
        } else {
            condition.and(categoryEntity.status.eq(searchDto.getStatus()));
        }
        List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll(condition);
        return categories.stream().map(this::transformToCategoryResponseDto).collect(Collectors.toList());
    }

    @Override
    public Page<CategoryResponseDto> search(CategorySearchDto categorySearchDto, Pageable pageable) {
        BooleanBuilder condition = new BooleanBuilder();
        QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;
        if (Objects.nonNull(categorySearchDto.getName())) {
            condition.and(categoryEntity.name.likeIgnoreCase("%" + categorySearchDto.getName().trim() + "%"));
        }
        condition.and(categoryEntity.deleteFlag.eq(DeleteFlag.NOT_DELETE.getVal()));
        return categoryRepository.findAll(condition, pageable).map(this::transformToCategoryResponseDto);
    }

    @Override
    public CategoryResponseDto findById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> Exception.dataNotFound()
                        .build("Category not found", HttpStatus.NOT_FOUND.value()));

        return modelMapper.map(categoryEntity, CategoryResponseDto.class);
    }

    @Override
    public void add(CategoryAddDto dto) {
        CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
        entity.setStatus(StatusType.ENABLE.getVal());
        categoryRepository.save(entity);
    }

    @Override
    public void edit(CategoryAddDto dto, Integer id) {
        CategoryEntity entity = categoryRepository.findById(id).get();
        entity.setName(dto.getName());
        categoryRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        CategoryEntity entity = categoryRepository.findById(id).get();
        entity.setStatus(StatusType.DELETED.getVal());
        categoryRepository.save(entity);
    }

    @Override
    public void updateEnableFlag(Integer categoryId, Integer status) {
        CategoryEntity entity = categoryRepository.findByIdAndStatusNot(categoryId, StatusType.DELETED.getVal());
        if (!Objects.nonNull(entity)) {
            throw Exception.dataNotFound().build("Category not found", HttpStatus.NOT_FOUND.value());
        }
        entity.setStatus(status);
        categoryRepository.save(entity);
    }

    private CategoryResponseDto transformToCategoryResponseDto(CategoryEntity entity) {
        CategoryResponseDto responseDto = modelMapper.map(entity, CategoryResponseDto.class);
        Integer productCount = productRepository.countByCategoryIdAndStatusEquals(entity.getId(), ProductStatus.IN_STOCK.getVal());
        responseDto.setProductCount(productCount);

        return responseDto;
    }
}
