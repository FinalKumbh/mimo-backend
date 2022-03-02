package com.kumbh.mimo.domain.item;

import com.kumbh.mimo.dto.item.ItemListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    @Query("select new com.kumbh.mimo.dto.item.ItemListDto(i.id, i.itemName, i.itemDetail, i.skintype, i.skintone, ii.imgUrl) " +
            "from ItemImg ii " +
            "join Item i " +
            "on (i.id = ii.item.id)"
    )
    List<ItemListDto> getAllItems();
}
