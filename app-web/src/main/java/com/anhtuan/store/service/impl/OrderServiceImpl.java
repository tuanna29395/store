package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.*;
import com.anhtuan.store.commons.utils.DateTimeUtils;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderRqDto;
import com.anhtuan.store.dto.request.SearchOrderDto;
import com.anhtuan.store.dto.response.CartItemDto;
import com.anhtuan.store.dto.response.OrderItemResponseDto;
import com.anhtuan.store.dto.response.OrderResponseDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.*;
import com.anhtuan.store.repository.*;
import com.anhtuan.store.service.CartService;
import com.anhtuan.store.service.CommonService;
import com.anhtuan.store.service.OrderService;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommonService commonService;

    @Override
    @Transactional
    public void orderProduct(OrderRqDto dto, Principal principle, HttpSession session) {

        OrderEntity orderEntity = createOrder(dto, principle);
        createOrderItem(session, orderEntity);
    }

    @Override
    public List<OrderResponseDto> getAll(SearchOrderDto dto) {
        BooleanBuilder condition = new BooleanBuilder();
        QOrderEntity orderEntity = QOrderEntity.orderEntity;
        if (dto.getStatus() != null) {
            condition.and(orderEntity.status.eq(dto.getStatus()));
        }
        if (dto.getFromDate() != null && dto.getEndDate() != null) {
            Date from = DateTimeUtils.addTime(dto.getFromDate(), 0, 0, 0);
            Date end = DateTimeUtils.addTime(dto.getEndDate(), 23, 59, 59);
            condition.and(orderEntity.updatedAt.between(from, end));
        }
        return Lists.newArrayList(orderRepository.findAll(condition)).stream()
                .map(this::transformToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemById(Integer orderId) {
        return orderItemsRepository.findByOrderId(orderId).stream().map(this::transformToOrderItemDto).collect(Collectors.toList());
    }

    @Override
    public void changeStatus(Integer id, Integer status) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        assert orderEntity != null;
        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
    }

    private OrderEntity createOrder(OrderRqDto dto, Principal principle) {
        OrderEntity orderEntity = new OrderEntity();

        UserEntity userEntity = userRepository.findByIdAndDeletedFlag(principle.getId(), UserStatus.NOT_DELETE.getValue())
                .orElseThrow(() -> Exception.dataNotFound()
                        .build(String.format(ErrorMessage.User.USER_NOT_FOUND, principle.getEmail())));

        orderEntity.setUser(userEntity);
        if (dto.getTypePayment().equals(PaymentType.PAY_WHEN_RECEIVED.getType())) {
            orderEntity.setPayment(PaymentType.PAY_WHEN_RECEIVED.getValue());
            orderEntity.setStatus(OrderStatus.PROCESSING.getValue());
        } else {
            orderEntity.setPayment(PaymentType.PAY_PAL.getValue());
            orderEntity.setStatus(OrderStatus.COMPLETED.getValue());
        }
        orderEntity.setOrderName(dto.getFullName());
        orderEntity.setOrderPhone(dto.getPhoneNumber());
        orderEntity.setOrderAddress(dto.getAddress());

        return orderRepository.save(orderEntity);
    }

    private void createOrderItem(HttpSession session, OrderEntity orderEntity) {
        Integer orderId = orderEntity.getId();

        List<CartItemDto> cartItemDtos = cartService.getAll(session);
        if (cartItemDtos.isEmpty()) {
            throw Exception.dataConflict().build("Data conflict");
        }

        Set<OrderItemEntity> orderItemEntities = cartService.getAll(session).stream().map(cartItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();

            ProductEntity productEntity = productRepository.findByIdAndDeleteFlagAndStatus(cartItem.getProduct().getId(), DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
                    .orElseThrow(() -> Exception.dataNotFound()
                            .build("Product not found"));

            DiscountEntity discountEntity = productEntity.getDiscount();
            if (commonService.isValidDiscount(discountEntity)) {
                orderItemEntity.setPercentDiscount(discountEntity.getPercent());
            } else {
                orderItemEntity.setPercentDiscount(0);
            }
            SizeEntity sizeEntity = sizeRepository.findById(cartItem.getSize().getId())
                    .orElseThrow(() -> Exception.dataNotFound()
                            .build("Data not found"));

            OrderItemId id = new OrderItemId();
            id.setOrderId(orderId);
            id.setProductId(cartItem.getProduct().getId());

            orderItemEntity.setId(id);
            orderItemEntity.setProduct(productEntity);
            orderItemEntity.setSize(sizeEntity);

            orderItemEntity.setOrder(orderEntity);

            orderItemEntity.setQuantity(cartItem.getQuantity());
            orderItemEntity.setSoldPrice(productEntity.getSalePrice());
//            orderItemEntity.setSizePrice(sizeEntity.getPrice());
            orderItemEntity.setAmount(convertPrice(cartItem.getAmount()));


            return orderItemEntity;
        }).collect(Collectors.toSet());

        orderItemsRepository.saveAll(orderItemEntities);
    }

    private Integer convertPrice(String price) {
        return Integer.parseInt(price.replace(",", ""));
    }

    private OrderResponseDto transformToResponseDto(OrderEntity entity) {
        OrderResponseDto res = modelMapper.map(entity, OrderResponseDto.class);
        res.setTotalPrice(orderRepository.calculateAmountById(entity.getId()));
        res.setStatus(getStatus(entity.getStatus()));
        res.setStatusNumber(entity.getStatus());
        return res;
    }

    private String getStatus(Integer status) {
        OrderStatus orderStatus = OrderStatus.values()[status];
        switch (orderStatus) {
            case COMPLETED:
                return "Completed";
            case PROCESSING:
                return "Processing";

            default:
                return "Cancel";
        }
    }

    private OrderItemResponseDto transformToOrderItemDto(OrderItemEntity orderItemEntity) {
        OrderItemResponseDto res = modelMapper.map(orderItemEntity, OrderItemResponseDto.class);
        res.setProduct(commonService.transformProductEntityToDto(orderItemEntity.getProduct()));
        return res;
    }

}
