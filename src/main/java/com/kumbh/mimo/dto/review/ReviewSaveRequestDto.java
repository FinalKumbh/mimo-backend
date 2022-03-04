package com.kumbh.mimo.dto.review;


import com.kumbh.mimo.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private String content;
    private String author;

    @Builder
    public ReviewSaveRequestDto(String title, String content, String author){
        this.content = content;
        this.author = author;
    }

    public Review toEntity(){
        return Review.builder()
                .content(content)
                .author(author)
                .build();
    }
}