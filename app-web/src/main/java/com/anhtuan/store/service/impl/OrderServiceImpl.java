package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.dto.request.OrderProductRqDto;
import com.anhtuan.store.exception.ErrorObject;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.OrderEntity;
import com.anhtuan.store.model.SizeEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.OrderRepository;
import com.anhtuan.store.repository.SizeRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public void orderProduct(OrderProductRqDto dto) {
        OrderEntity orderEntity = orderRepository.findByUserId(dto.getUserId());

        if (Objects.isNull(orderEntity)) {
            Integer userId = dto.getUserId();
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.User.USER_NOT_FOUND, userId)));
            OrderEntity entity = new OrderEntity();
            entity.setUser(user);
            orderEntity = orderRepository.save(entity);
        }

        Integer sizeId = dto.getSizeId();
        if (Objects.nonNull(sizeId)) {
            SizeEntity size = sizeRepository.findById(dto.getSizeId()).orElseThrow(() -> Exception.dataNotFound().build("Size not found"));
        }

    }
}
