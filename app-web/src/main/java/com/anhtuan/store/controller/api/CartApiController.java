package com.anhtuan.store.controller.api;

import com.anhtuan.store.dto.request.CartItemReqUpdateDto;
import com.anhtuan.store.dto.response.CartIdDto;
import com.anhtuan.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/carts")
public class CartApiController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<?> getAll(HttpSession session) {
        return ResponseEntity.ok(cartService.getAll(session));
    }

    @PostMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@RequestBody CartIdDto cartIdDto, HttpSession session) {
        cartService.removeItem(session, cartIdDto);
    }

    @PostMapping("/update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody CartItemReqUpdateDto dtoUpdate, HttpSession session) {
        cartService.updateItem(session, dtoUpdate);
    }

}

