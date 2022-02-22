package com.kumbh.mimo.dto.post;


import com.kumbh.mimo.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
