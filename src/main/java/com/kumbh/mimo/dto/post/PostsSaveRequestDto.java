package com.kumbh.mimo.dto.post;

import com.kumbh.mimo.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .content(content)
                .author(author)
                .build();
    }
}