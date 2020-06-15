package com.anhtuan.store.service.impl;

import com.anhtuan.store.dto.request.ProductAddEditDto;
import com.anhtuan.store.dto.response.CategoryResponseDto;
import com.anhtuan.store.dto.response.DiscountResponseDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.service.CommonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponseDto transformProductEntityToDto(ProductEntity productEntity) {
        ProductResponseDto res = modelMapper.map(productEntity, ProductResponseDto.class);
        CategoryResponseDto categoryResponseDto = modelMapper.map(productEntity.getCategory(), CategoryResponseDto.class);
        DiscountResponseDto discountResponseDto = new DiscountResponseDto();

        if (Objects.nonNull(productEntity.getDiscount())) {
            discountResponseDto = modelMapper.map(productEntity.getDiscount(), DiscountResponseDto.class);
        }

        Integer salePrice = productEntity.getSalePrice();
        res.setSalePrice(String.format("%,d", salePrice));
        res.setCategory(categoryResponseDto);
        res.setDiscount(discountResponseDto);
        return res;
    }

    @Override
    public ProductAddEditDto transformToAdminProductDtoUpdate(ProductEntity productEntity) {
        ProductAddEditDto res = modelMapper.map(productEntity, ProductAddEditDto.class);
        res.setCategoryId(productEntity.getCategory().getId());
        res.setDescription(productEntity.getDescription());
        if (Objects.nonNull(productEntity.getDiscount())) {
            res.setDiscountId(productEntity.getDiscount().getId());
        }

        return res;
    }
}
