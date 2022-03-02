package com.kumbh.mimo.dto.item;

import com.kumbh.mimo.domain.constant.SkinTone;
import com.kumbh.mimo.domain.constant.SkinType;
import com.kumbh.mimo.domain.item.Item;
import com.kumbh.mimo.domain.item.ItemImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListDto {
    private Long itemId;
    private String itemName;
    private String itemDetail;
    private SkinType skintype;
    private SkinTone skintone;
    private String imgUrl;

    public ItemListDto(Long itemId, String itemName, String itemDetail, SkinType skintype, SkinTone skintone, String imgUrl){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.skintype = skintype;
        this.skintone = skintone;
        this.imgUrl = imgUrl;
    }

}
