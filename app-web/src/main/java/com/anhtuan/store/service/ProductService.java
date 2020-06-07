package com.anhtuan.store.service;

import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.response.ReviewResponseDto;
import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.dto.request.ReviewReqDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponseDto> search(ProductSearchRqDto searchRqDto, Pageable pageable);

    ProductResponseDto findById(Integer productId);

    Long getMaxPrice();

    Long getMinPrice();

    void addReview(Principal principal, ReviewReqDto dto, Integer productId);

    Page<ReviewResponseDto> listReview(Integer productId, Pageable pageable);
}
