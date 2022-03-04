package com.kumbh.mimo.dto.review;

import com.kumbh.mimo.domain.review.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

//    public ReviewListResponseDto(Review entity){
//        this.id = entity.getId();
//        this.author = entity.getAuthor();
//        this.modifiedDate = entity.getModifiedDate();
//    }
}