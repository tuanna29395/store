package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.DiscountStatus;
import com.anhtuan.store.commons.utils.DateTimeUtils;
import com.anhtuan.store.dto.request.DiscountRequestAddDto;
import com.anhtuan.store.dto.request.DiscountRequestSearchDto;
import com.anhtuan.store.dto.response.DiscountResponseDto;
import com.anhtuan.store.model.DiscountEntity;
import com.anhtuan.store.model.QDiscountEntity;
import com.anhtuan.store.repository.DiscountRepository;
import com.anhtuan.store.service.CommonService;
import com.anhtuan.store.service.DiscountService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    CommonService commonService;

    @Override
    public List<DiscountResponseDto> getAll() {

        return discountRepository.findAllByDeleteFlag(DeleteFlag.NOT_DELETE.getVal())
                .stream().map(discountEntity -> modelMapper.map(discountEntity, DiscountResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<DiscountResponseDto> search(DiscountRequestSearchDto dto, Pageable pageable) {
        BooleanBuilder condition = new BooleanBuilder();

        QDiscountEntity discountEntity = QDiscountEntity.discountEntity;
        Date now = new Date();
        if (dto.getStatus().equals(DiscountStatus.ACTIVE.getVal())) {
            condition.and(discountEntity.startAt.before(now)).and(discountEntity.endAt.after(now));
        } else {
            if (dto.getStatus().equals(DiscountStatus.IN_ACTIVE.getVal())) {
                condition.and(discountEntity.startAt.after(now)).or(discountEntity.endAt.before(now));
            }
        }
        condition.and(discountEntity.deleteFlag.eq(DeleteFlag.NOT_DELETE.getVal()));

        return discountRepository.findAll(condition, pageable).map(entity -> commonService.transformDiscountDto(entity));
    }

    @Override
    public void create(DiscountRequestAddDto dto) {
        DiscountEntity discountEntity = modelMapper.map(dto, DiscountEntity.class);
        discountEntity.setDeleteFlag(DeleteFlag.NOT_DELETE.getVal());
        discountRepository.save(discountEntity);
    }

    @Override
    public void update(Integer id, DiscountRequestAddDto dto) {
        DiscountEntity discountEntity = discountRepository.findById(id).get();

        discountEntity.setPercent(dto.getPercent());
        discountEntity.setStartAt(dto.getStartAt());
        discountEntity.setEndAt(dto.getEndAt());
        discountEntity.setDescription(dto.getDescription());

        discountRepository.save(discountEntity);
    }

    @Override
    public DiscountResponseDto findById(Integer id) {
        return commonService.transformDiscountDto(discountRepository.findById(id).get());
    }

}
