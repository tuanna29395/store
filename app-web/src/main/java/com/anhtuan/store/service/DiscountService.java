package com.anhtuan.store.service;

import com.anhtuan.store.dto.response.DiscountResponseDto;

import java.util.List;

public interface DiscountService {
    List<DiscountResponseDto> getAll();
}
