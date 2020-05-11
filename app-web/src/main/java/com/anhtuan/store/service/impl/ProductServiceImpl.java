package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.model.QProductEntity;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.service.ProductService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ProductResponseDto> search(ProductSearchRqDto searchRqDto, Pageable pageable) {
        return productRepository.findAll(buildCondition(searchRqDto), pageable).map(this::transformProductEntityToDto);
    }

    private BooleanBuilder buildCondition(ProductSearchRqDto searchRqDto) {
        BooleanBuilder condition = new BooleanBuilder();
        QProductEntity productEntity = QProductEntity.productEntity;
        if (Objects.nonNull(searchRqDto.getCategoryId())) {
            condition.and(productEntity.category.id.eq(searchRqDto.getCategoryId()));
        }
        if (Objects.nonNull(searchRqDto.getName())){
            condition.and(productEntity.name.containsIgnoreCase(searchRqDto.getName()));
        }
        condition.and(productEntity.deleteFlag.eq(DeleteFlag.NOT_DELETE.getVal()));
        return condition;
    }

    private ProductResponseDto transformProductEntityToDto(ProductEntity productEntity) {
        ProductResponseDto res = modelMapper.map(productEntity, ProductResponseDto.class);
        Integer salePrice = productEntity.getSalePrice();
        res.setSalePrice(String.format("%,d", salePrice));
        return res;
    }
}
