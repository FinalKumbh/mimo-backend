package com.kumbh.mimo.domain.item;

import com.kumbh.mimo.domain.BaseEntity;
import com.kumbh.mimo.domain.constant.ItemSellStatus;
import com.kumbh.mimo.domain.constant.SkinTone;
import com.kumbh.mimo.domain.constant.SkinType;
import com.kumbh.mimo.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="item")
@Getter @Setter
@ToString
public class Item extends BaseEntity {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;                            //상품코드

    @Column(nullable = false)
    private String color;                       //상품컬러 hex값

    @Lob
    @Column(nullable = false)
    private String itemDetail;                  //상품 상세 설명

    @Column(nullable = false, length = 50)
    private String itemName;                      //상품명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;      //상품 판매 상태

    @Column(name = "price", nullable = false)
    private int price;                          //가격

    @Column(nullable = false)
    private int stockNumber;                    //재고수량

    @Enumerated(EnumType.STRING)
    private SkinType skintype;

    @Enumerated(EnumType.STRING)
    private SkinTone skintone;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<Posts> posts;
}
