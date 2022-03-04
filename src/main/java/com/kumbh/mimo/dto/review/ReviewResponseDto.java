package com.kumbh.mimo.dto.review;

import com.kumbh.mimo.domain.review.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public ReviewResponseDto(Review entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
