package com.kumbh.mimo.domain.item;

import com.kumbh.mimo.dto.item.ItemListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    @Query(value = "select i.item_id as itemId, i.item_name as itemName, i.item_detail as itemDetail, i.skintype, i.skintone, ii.img_url as imgUrl" +
            " from item_img ii" +
            " join item i" +
            " where i.item_id = ii.item_id;", nativeQuery = true
    )
    List<ItemListDto> getAllItems();

}
