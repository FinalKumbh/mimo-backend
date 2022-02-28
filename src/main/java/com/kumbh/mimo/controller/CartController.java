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

    @GetMapping("/{email}")
    public ResponseEntity<?> getCart(@PathVariable String email){
        String result = "";

        try{
            List<CartDetailDto> cartDetailList = cartService.getCartList(email);
            result = "cartDetailList : " + cartDetailList + " Get OK";
            return new ResponseEntity<List>(cartDetailList, HttpStatus.OK);
        } catch(Exception e){
            result = "cartDetailList Get Fail";
            return new ResponseEntity<List>(HttpStatus.NO_CONTENT);
        }
    }



}
