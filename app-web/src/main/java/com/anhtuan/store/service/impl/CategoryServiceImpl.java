package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.ProductStatus;
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

    @Override
    public List<CategoryResponseDto> getAll() {
        List<CategoryEntity> categories = categoryRepository.findAll();
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

    private CategoryResponseDto transformToCategoryResponseDto(CategoryEntity entity) {
        CategoryResponseDto responseDto = modelMapper.map(entity, CategoryResponseDto.class);
        Integer productCount = productRepository.countByCategoryIdAndStatusEquals(entity.getId(), ProductStatus.IN_STOCK.getVal());
        responseDto.setProductCount(productCount);

        return responseDto;
    }
}
