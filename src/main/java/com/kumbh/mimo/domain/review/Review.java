package com.kumbh.mimo.domain.review;

import com.kumbh.mimo.domain.BaseEntity;
import com.kumbh.mimo.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int point;

    private String imageUrl;

    private String author;

    @Builder
    public Review(String content, int point, String imageUrl, String author) {
        this.content = content;
        this.point = point;
        this.imageUrl = imageUrl;
        this.author = author;
    }

    @Builder
    public Review(String content, int point, String author) {
        this.content = content;
        this.point = point;
        this.author = author;
    }

    public void update(String content) {
        this.content = content;
    }

}
