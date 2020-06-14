package com.anhtuan.store.service.impl;

import com.anhtuan.store.dto.response.CategoryResponseDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.service.CommonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponseDto transformProductEntityToDto(ProductEntity productEntity) {
        ProductResponseDto res = modelMapper.map(productEntity, ProductResponseDto.class);
        CategoryResponseDto categoryResponseDto = modelMapper.map(productEntity.getCategory(), CategoryResponseDto.class);
        Integer salePrice = productEntity.getSalePrice();
        res.setSalePrice(String.format("%,d", salePrice));
        res.setCategory(categoryResponseDto);
        return res;
    }
}
