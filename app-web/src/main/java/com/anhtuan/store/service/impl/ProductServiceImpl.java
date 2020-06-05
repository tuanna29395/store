package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.model.QProductEntity;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.service.CommonService;
import com.anhtuan.store.service.ProductService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CommonService commonService;

    @Override
    public Page<ProductResponseDto> search(ProductSearchRqDto searchRqDto, Pageable pageable) {
        return productRepository.findAll(buildCondition(searchRqDto), pageable).map(entity -> commonService.transformProductEntityToDto(entity));
    }

    @Override
    public ProductResponseDto findById(Integer productId) {
        ProductEntity productEntity = productRepository.findByIdAndAndDeleteFlagAndStatus(productId, DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
                .orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, productId), HttpStatus.NOT_FOUND.value()));

        return commonService.transformProductEntityToDto(productEntity);
    }

    @Override
    public Long getMaxPrice() {
        return (long) productRepository.findTopByOrderBySalePriceDesc();
    }

    @Override
    public Long getMinPrice() {
        return (long) productRepository.findTopByOrderBySalePriceAsc();
    }

    private BooleanBuilder buildCondition(ProductSearchRqDto searchRqDto) {
        BooleanBuilder condition = new BooleanBuilder();
        QProductEntity productEntity = QProductEntity.productEntity;
        if (Objects.nonNull(searchRqDto.getCategoryId())) {
            condition.and(productEntity.category.id.eq(searchRqDto.getCategoryId()));
        }
        if (Objects.nonNull(searchRqDto.getName())) {
            condition.and(productEntity.name.containsIgnoreCase(searchRqDto.getName()));
        }
        if (Objects.nonNull(searchRqDto.getMinPrice())) {
            condition.and(productEntity.salePrice.goe(searchRqDto.getMinPrice()));
        }
        if (Objects.nonNull(searchRqDto.getMaxPrice())) {
            condition.and(productEntity.salePrice.loe(searchRqDto.getMaxPrice()));
        }
        condition.and(productEntity.deleteFlag.eq(DeleteFlag.NOT_DELETE.getVal()));
        return condition;
    }

}
