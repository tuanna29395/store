package com.anhtuan.store.service;

import com.anhtuan.store.dto.request.ProductSearchRqDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponseDto> search(ProductSearchRqDto searchRqDto, Pageable pageable);
}
