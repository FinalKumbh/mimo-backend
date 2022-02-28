package com.kumbh.mimo.service;


import com.kumbh.mimo.domain.cart.Cart;
import com.kumbh.mimo.domain.cart.CartItem;
import com.kumbh.mimo.domain.cart.CartItemRepository;
import com.kumbh.mimo.domain.cart.CartRepository;
import com.kumbh.mimo.domain.item.Item;
import com.kumbh.mimo.domain.item.ItemRepository;
import com.kumbh.mimo.domain.user.User;
import com.kumbh.mimo.domain.user.UserRepository;
import com.kumbh.mimo.dto.cart.CartDetailDto;
import com.kumbh.mimo.dto.cart.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Long addCart(CartItemDto cartItemDto, String email){

        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findByEmail(email).orElseThrow(()-> new
                IllegalArgumentException("해당 유저가 없습니다. email="+ email));

        Cart cart = cartRepository.findByUserEmail(user.getEmail());
        if(cart == null){
            cart = Cart.createCart(user);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        User user = userRepository.findByEmail(email).orElseThrow(()-> new
                IllegalArgumentException("해당 유저가 없습니다. email="+ email));
        Cart cart = cartRepository.findByUserEmail(user.getEmail());
        if(cart == null){
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        return cartDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){

        User currentUser = userRepository.findByEmail(email).orElseThrow(()-> new
                IllegalArgumentException("해당 유저가 없습니다. email="+ email));
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        User savedUser = cartItem.getCart().getUser();

        if(!StringUtils.equals(currentUser.getEmail(), savedUser.getEmail())){
            return false;
        }

        return true;
    }

    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }
}
