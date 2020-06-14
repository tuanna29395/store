package com.anhtuan.store.service.impl;

import com.anhtuan.store.commons.constants.ErrorMessage;
import com.anhtuan.store.commons.enums.DeleteFlag;
import com.anhtuan.store.commons.enums.ProductStatus;
import com.anhtuan.store.dto.request.CartItemReqUpdateDto;
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
import org.springframework.http.HttpStatus;
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
        ProductEntity product = productRepository.findByIdAndDeleteFlagAndStatus(productId, DeleteFlag.NOT_DELETE.getVal(), ProductStatus.IN_STOCK.getVal())
                .orElseThrow(() -> Exception.dataNotFound()
                        .build(String.format(ErrorMessage.Product.PRODUCT_NOT_FOUND, productId), HttpStatus.NOT_FOUND.value()));
        SizeEntity sizeEntity = new SizeEntity();

        if (Objects.nonNull(toppingReq) && toppingReq.getSizeId() != null)
            sizeEntity = sizeRepository.findById(toppingReq.getSizeId()).orElseThrow(() -> Exception.dataNotFound().build("Size not found"));

        ProductResponseDto productDto = commonService.transformProductEntityToDto(product);
        SizeDto sizeDto = modelMapper.map(sizeEntity, SizeDto.class);
        CartIdDto cartIdDto = new CartIdDto(productId, toppingReq.getSizeId());
        CartItemDto item;
        if (cartItems.containsKey(cartIdDto)) {
            item = cartItems.get(cartIdDto);
            item.setProduct(productDto);
            item.setQuantity(item.getQuantity() + toppingReq.getQuantity());
        } else {
            item = new CartItemDto();
            item.setProduct(productDto);
            item.setQuantity(toppingReq.getQuantity());

        }
        item.setSize(sizeDto);
        item.setAmount(item.calculateAmount());
        cartItems.put(cartIdDto, item);

        return cartItems;
    }

    @Override
    public List<CartItemDto> getAll(HttpSession session) {
        HashMap<CartIdDto, CartItemDto> cartItems = (HashMap<CartIdDto, CartItemDto>) session.getAttribute(CART_NAME);
        return cartItems == null ? new ArrayList<>() : new ArrayList<>(cartItems.values());
    }

    @Override
    public void removeItem(HttpSession session, CartIdDto cartIdDto) {
        HashMap<CartIdDto, CartItemDto> cartItems = (HashMap<CartIdDto, CartItemDto>) session.getAttribute(CART_NAME);
        if (cartItems == null) {
            return;
        }
        cartItems.remove(cartIdDto);
    }

    @Override
    public void updateItem(HttpSession session, CartItemReqUpdateDto dto) {
        HashMap<CartIdDto, CartItemDto> cartItems = (HashMap<CartIdDto, CartItemDto>) session.getAttribute(CART_NAME);
        if (cartItems == null) {
            return;
        }
        Integer idSizeNew = dto.getSizeIdNew();
        SizeEntity sizeEntityNew = sizeRepository.findById(idSizeNew).orElseThrow(() -> Exception.dataNotFound().build("Size not found"));
        SizeDto sizeNewDto = modelMapper.map(sizeEntityNew, SizeDto.class);
        CartIdDto cartIdOld = new CartIdDto(dto.getProductId(), dto.getSizeIdOld());
        CartItemDto cartItemOld = cartItems.get(cartIdOld);
        if (cartItemOld == null) return;
        if (0 == dto.getQuantity()) {
            cartItems.remove(cartIdOld);
            return;
        }
        CartIdDto cartIdNew = new CartIdDto(dto.getProductId(), dto.getSizeIdNew());
        if (cartItems.containsKey(cartIdNew)) {

            CartItemDto cartItemNew = cartItems.get(cartIdNew);

            cartItemNew.setSize(sizeNewDto);
            cartItemNew.setProduct(cartItemOld.getProduct());
            if (idSizeNew.equals(cartIdOld.getSizeId())) {
                cartItemNew.setQuantity(dto.getQuantity());
            } else {
                cartItemNew.setQuantity(cartItemNew.getQuantity() + dto.getQuantity());
            }


            cartItemNew.setAmount(cartItemNew.calculateAmount());

            cartItems.remove(cartIdOld);
            cartItems.put(cartIdNew, cartItemNew);
        } else {
            CartItemDto cartItemNew = new CartItemDto();
            cartItemNew.setSize(sizeNewDto);
            cartItemNew.setProduct(cartItemOld.getProduct());
            if (dto.getQuantity() != null) {
                cartItemNew.setQuantity(dto.getQuantity());
            } else {
                cartItemNew.setQuantity(cartItemOld.getQuantity());
            }
            cartItemNew.setAmount(cartItemNew.calculateAmount());

            cartItems.put(cartIdNew, cartItemNew);
            cartItems.remove(cartIdOld);

        }
    }

    @Override
    public String totalCart(HttpSession session) {
        HashMap<CartIdDto, CartItemDto> cartItems = (HashMap<CartIdDto, CartItemDto>) session.getAttribute(CART_NAME);
        if (Objects.isNull(cartItems)) return "0";
        return String.format("%,d", cartItems.values().stream().mapToInt(this::calculateAmount).sum());
    }

    @Override
    public Integer calculateTotalCart(HttpSession session) {
        HashMap<CartIdDto, CartItemDto> cartItems = (HashMap<CartIdDto, CartItemDto>) session.getAttribute(CART_NAME);
        if (Objects.isNull(cartItems)) return 0;
        return cartItems.values().stream().mapToInt(this::calculateAmount).sum();
    }

    private Integer calculateAmount(CartItemDto cartItemDto) {
        return (convertPrice(cartItemDto.getProduct().getSalePrice()) + cartItemDto.getSize().getPrice()) * cartItemDto.getQuantity();
    }

    private Integer convertPrice(String price) {
        return Integer.parseInt(price.replace(",", ""));
    }

}
