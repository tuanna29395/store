package com.anhtuan.store.service.impl;

import com.anhtuan.store.dto.response.SizeDto;
import com.anhtuan.store.repository.SizeRepository;
import com.anhtuan.store.service.SizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SizeDto> getAll() {
        return sizeRepository.findAll().stream()
                .map(s -> modelMapper.map(s, SizeDto.class))
                .collect(Collectors.toList());
    }
}
