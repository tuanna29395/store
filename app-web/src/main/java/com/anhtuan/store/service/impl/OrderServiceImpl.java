package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.OrderStatus;
import com.anhtuan.store.commons.enums.PaymentType;
import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.commons.enums.UserStatus;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderRqDto;
import com.anhtuan.store.dto.response.CartItemDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.model.OrderEntity;
import com.anhtuan.store.model.OrderItemEntity;
import com.anhtuan.store.model.OrderItemId;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.model.SizeEntity;
import com.anhtuan.store.model.UserEntity;
import com.anhtuan.store.repository.OrderItemsRepository;
import com.anhtuan.store.repository.OrderRepository;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.repository.SizeRepository;
import com.anhtuan.store.repository.UserRepository;
import com.anhtuan.store.service.CartService;
import com.anhtuan.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
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

    @Override
    @Transactional
    public void orderProduct(OrderRqDto dto, Principal principle, HttpSession session) {

        OrderEntity orderEntity = createOrder(dto, principle);
        createOrderItem(session, orderEntity);
    }

    private OrderEntity createOrder(OrderRqDto dto, Principal principle) {
        OrderEntity orderEntity = new OrderEntity();

        UserEntity userEntity = userRepository.findByEmailAndDeletedFlag(principle.getEmail(), UserStatus.NOT_DELETE.getValue())
                .orElseThrow(() -> Exception.dataNotFound()
                        .build(String.format(ErrorMessage.User.USER_NOT_FOUND, principle.getEmail())));

        orderEntity.setUser(userEntity);
        orderEntity.setPayment(PaymentType.PAY_PAL.getValue());
        orderEntity.setOrderName(dto.getFullName());
        orderEntity.setOrderPhone(dto.getPhoneNumber());
        orderEntity.setOrderAddress(dto.getAddress());
        orderEntity.setStatus(OrderStatus.PROCESSING.getValue());

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

            ProductEntity productEntity = productRepository.findByIdAndAndDeleteFlagAndStatus(cartItem.getProduct().getId(), DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
                    .orElseThrow(() -> Exception.dataNotFound()
                            .build("Product not found"));

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
            orderItemEntity.setSizePrice(sizeEntity.getPrice());
            orderItemEntity.setAmount(convertPrice(cartItem.getAmount()));


            return orderItemEntity;
        }).collect(Collectors.toSet());

        orderItemsRepository.saveAll(orderItemEntities);
    }

    private Integer convertPrice(String price) {
        return Integer.parseInt(price.replace(",", ""));
    }

}
