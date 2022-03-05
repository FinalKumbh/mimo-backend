package com.kumbh.mimo.dto.review;

import com.kumbh.mimo.domain.review.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {
    private Long   reviewId;
    private String content;
    private String name;
    private int    point;
    private String imageUrl;

    public ReviewResponseDto(Long reviewId, String content, String name, int point, String imageUrl){
        this.reviewId = reviewId;
        this.content = content;
        this.name = name;
        this.point = point;
        this.imageUrl = imageUrl;
    }
}
