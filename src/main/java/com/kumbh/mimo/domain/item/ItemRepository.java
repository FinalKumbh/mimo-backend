package com.kumbh.mimo.domain.item;

import com.kumbh.mimo.domain.item.Item;
import com.kumbh.mimo.dto.item.ItemListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByItemName(String itemName);
    List<Item> findByItemNameOrItemDetail(String itemNm, String itemDetail);
    List<Item> findByPriceLessThan(Integer price);
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);


//    @Query("select new com.kumbh.mimo.dto.item.ItemListDto(i.item_id, i.item_name, i.item_detail, i.skintype, i.skintone, ii.img_url) " +
//            "FROM item i " +
//            "JOIN item_img ii " +
//            "WHERE i.item_id = ii.item_id"
//    )
//    List<ItemListDto> getAllItems();
}
