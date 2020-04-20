package com.anhtuan.store.service;

import com.anhtuan.store.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAll();
}
