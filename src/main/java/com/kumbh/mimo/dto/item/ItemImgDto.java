package com.kumbh.mimo.dto.item;

import com.kumbh.mimo.domain.item.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String imgUrl;

    private String oriImgName;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    //상품 등록 시 화면으로부터 전달받은 DTO 객체를 엔티티 객체로 변환
    //상품 조회 시 엔티티 객체를 DTO 객체로 변환
    //위 2 작업은 반복적인 작업이므로 modelmapper 라이브러리로 자동처리
    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg,ItemImgDto.class);
    }

}