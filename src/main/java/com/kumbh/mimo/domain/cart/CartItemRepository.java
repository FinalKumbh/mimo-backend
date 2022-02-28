package com.kumbh.mimo.domain.cart;

import com.kumbh.mimo.dto.cart.CartDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query(value = "select new com.kumbh.mimo.dto.cart.CartDetailDto(ci.id, i.itemName, i.color, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc", nativeQuery = true
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);
}
