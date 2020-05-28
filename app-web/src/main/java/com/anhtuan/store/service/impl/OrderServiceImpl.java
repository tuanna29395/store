package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderRqDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.OrderEntity;
import com.anhtuan.store.model.OrderItemEntity;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.model.SizeEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.OrderItemsRepository;
import com.anhtuan.store.repository.OrderRepository;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.repository.SizeRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    @Transactional
    public void orderProduct(OrderRqDto dto, Principal principle) {

//        OrderEntity orderEntity = orderRepository.findByUserId(principle.getId());
//        OrderItemEntity orderItemEntity = new OrderItemEntity();
//        Integer sizeId = dto.getSizeId();
//
//        if (Objects.isNull(orderEntity)) {
//            Integer userId = principle.getId();
//            UserEntity user = userRepository.findById(userId).orElseThrow(() -> Exception.dataNotFound().build(String.format(ErrorMessage.User.USER_NOT_FOUND, userId)));
//            OrderEntity entity = new OrderEntity();
//            entity.setUser(user);
//            orderEntity = orderRepository.save(entity);
//        }
//
//        if (Objects.nonNull(sizeId)) {
//            SizeEntity size = sizeRepository.findById(dto.getSizeId()).orElseThrow(() -> Exception.dataNotFound().build("Size not found"));
//            orderItemEntity.setSize(size);
//            orderItemEntity.setSizePrice(size.getPrice());
//        }
//
//        ProductEntity productEntity = productRepository.findByIdAndAndDeleteFlagAndStatus(dto.getProductId(), DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
//                .orElseThrow(() -> Exception.dataNotFound()
//                        .build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, dto.getProductId())));
//
//        orderItemEntity.setOrder(orderEntity);
//        orderItemEntity.setProduct(productEntity);
//        orderItemEntity.setSoldPrice(productEntity.getSalePrice());
//        orderItemEntity.setAmount(dto.getQuantity());
//
//        orderItemsRepository.save(orderItemEntity);

    }

}
