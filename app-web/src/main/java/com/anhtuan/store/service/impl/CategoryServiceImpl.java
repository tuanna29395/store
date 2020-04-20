package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.dto.response.CategoryResponseDto;
import com.anhtuan.store.model.CategoryEntity;
import com.anhtuan.store.repository.CategoryRepository;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    private CategoryResponseDto transformToCategoryResponseDto(CategoryEntity entity) {
        CategoryResponseDto responseDto = modelMapper.map(entity, CategoryResponseDto.class);
        Integer productCount = productRepository.countByCategoryIdAndStatusEquals(entity.getId(), ProductStatus.IN_STOCK.getVal());
        responseDto.setProductCount(productCount);

        return responseDto;
    }
}
