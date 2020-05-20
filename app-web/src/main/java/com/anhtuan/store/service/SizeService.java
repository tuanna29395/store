package com.anhtuan.store.service;

import com.anhtuan.store.dto.response.SizeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SizeService {
    List<SizeDto> getAll();
}
