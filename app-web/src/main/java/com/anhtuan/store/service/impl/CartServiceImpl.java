package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.dto.request.ToppingReq;
import com.anhtuan.store.dto.response.CartIdDto;
import com.anhtuan.store.dto.response.ProductResponseDto;
import com.anhtuan.store.dto.response.SizeDto;
import com.anhtuan.store.exception.Exception;
import com.anhtuan.store.dto.response.CartItemDto;
import com.anhtuan.store.model.ProductEntity;
import com.anhtuan.store.model.SizeEntity;
import com.anhtuan.store.repository.ProductRepository;
import com.anhtuan.store.repository.SizeRepository;
import com.anhtuan.store.service.CartService;
import com.anhtuan.store.service.CommonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ModelMapper modelMapper;

    public static final String CART_NAME = "myCartItems";

    @Override
    public Map<CartIdDto, CartItemDto> addProductToCart(HttpSession session, Integer productId, ToppingReq toppingReq) {

        HashMap<CartIdDto, CartItemDto> cartItems = (HashMap<CartIdDto, CartItemDto>) session.getAttribute(CART_NAME);
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        ProductEntity product = productRepository.findByIdAndAndDeleteFlagAndStatus(productId, DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
                .orElseThrow(() -> Exception.dataNotFound()
                        .build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, productId)));
        SizeEntity sizeEntity = new SizeEntity();

        if (Objects.nonNull(toppingReq) && toppingReq.getSizeId() != null)
            sizeEntity = sizeRepository.findById(toppingReq.getSizeId()).orElseThrow(() -> Exception.dataNotFound().build("Size not found"));

        ProductResponseDto productDto = commonService.transformProductEntityToDto(product);
        SizeDto sizeDto = modelMapper.map(sizeEntity, SizeDto.class);
        CartIdDto cartIdDto = new CartIdDto(productId, toppingReq.getSizeId());
        if (cartItems.containsKey(cartIdDto)) {
            CartItemDto item = cartItems.get(cartIdDto);
            item.setProduct(productDto);
            item.setQuantity(item.getQuantity() + toppingReq.getQuantity());
            item.setSize(sizeDto);
            item.setAmount(item.getAmount());
            cartItems.put(cartIdDto, item);
        } else {
            CartItemDto item = new CartItemDto();
            item.setProduct(productDto);
            item.setQuantity(toppingReq.getQuantity());
            item.setSize(sizeDto);
            item.setAmount(item.getAmount());
            cartItems.put(cartIdDto, item);

        }

        return cartItems;
    }

    @Override
    public List<CartItemDto> getAll(HttpSession session) {
        HashMap<Integer, CartItemDto> cartItems = (HashMap<Integer, CartItemDto>) session.getAttribute(CART_NAME);
        return cartItems == null ? new ArrayList<>() : new ArrayList<>(cartItems.values());
    }
}
