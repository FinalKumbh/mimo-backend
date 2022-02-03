package com.kumbh.mimo.domain.cart;

import com.kumbh.mimo.domain.BaseEntity;
import com.kumbh.mimo.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

//    public static Cart createCart(User user){
//        Cart cart = new Cart();
//        cart.setUser(user);
//        return cart;
//    }

}