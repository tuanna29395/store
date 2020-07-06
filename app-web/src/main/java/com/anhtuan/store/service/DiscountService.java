package com.anhtuan.store.service;

import com.anhtuan.store.dto.request.DiscountRequestAddDto;
import com.anhtuan.store.dto.request.DiscountRequestSearchDto;
import com.anhtuan.store.dto.response.DiscountResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountService {
    List<DiscountResponseDto> getAll();

    Page<DiscountResponseDto> search(DiscountRequestSearchDto dto, Pageable pageable);

    void create(DiscountRequestAddDto dto);

    void update(Integer id, DiscountRequestAddDto dto);

    DiscountResponseDto findById(Integer id);
}
