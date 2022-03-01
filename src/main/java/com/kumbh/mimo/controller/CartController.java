package com.kumbh.mimo.controller;

import com.kumbh.mimo.dto.cart.CartDetailDto;
import com.kumbh.mimo.dto.cart.CartItemDto;
import com.kumbh.mimo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/{email}")
    public ResponseEntity<?> initCart(@PathVariable String email, CartItemDto cartItemDto){
        Long cartItemId;

        try {
            cartItemId = cartService.addCart(cartItemDto, email);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

//    @GetMapping("/{email}")
//    public ResponseEntity<?> getCart(@PathVariable String email){
//        List<CartDetailDto> cartDetailList = cartService.getCartList(email);
//
//        if (cartDetailList == null){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(cartDetailList);
//    }

    @GetMapping("/{email}")
    public List<CartDetailDto> getCart(@PathVariable String email){
        return cartService.getCartList(email);
    }

//    @GetMapping("/{email}")
//    public ResponseEntity<?> getCart(@PathVariable String email){
//        String result = "";
//
//        try{
//            List<CartDetailDto> cartDetailList = cartService.getCartList(email);
//            return new ResponseEntity<List>(cartDetailList, HttpStatus.OK);
//        } catch(Exception e){
//            return new ResponseEntity<List>(HttpStatus.NO_CONTENT);
//        }
//    }



}
