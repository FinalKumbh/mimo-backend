package com.kumbh.mimo.dto.review;


import com.kumbh.mimo.domain.item.Item;
import com.kumbh.mimo.domain.review.Review;
import com.kumbh.mimo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private Long   userId;
    private Long   itemId;
    private String content;
    private int    point;
    private String imageUrl;

    @Builder
    public ReviewSaveRequestDto(Long userId, Long itemId, String content, int point){
        this.userId = userId;
        this.itemId = itemId;
        this.content = content;
        this.point = point;
    }

    @Builder
    public ReviewSaveRequestDto(Long userId, Long itemId, String content, int point, String imageUrl){
        this.userId = userId;
        this.itemId = itemId;
        this.content = content;
        this.point = point;
        this.imageUrl = imageUrl;
    }

    public Review toEntity(User user, Item item){
        return Review.builder()
                .user(user)
                .item(item)
                .content(content)
                .point(point)
                .imageUrl(imageUrl)
                .build();
    }
}