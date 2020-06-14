package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.dto.response.DiscountResponseDto;
import com.anhtuan.store.repository.DiscountRepository;
import com.anhtuan.store.service.DiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<DiscountResponseDto> getAll() {

        return discountRepository.findAllByDeleteFlag(DeleteFlag.NOT_DELETE.getVal())
                .stream().map(discountEntity -> modelMapper.map(discountEntity, DiscountResponseDto.class))
                .collect(Collectors.toList());
    }
}
