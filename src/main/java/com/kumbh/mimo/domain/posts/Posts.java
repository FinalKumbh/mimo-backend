package com.kumbh.mimo.domain.posts;

import com.kumbh.mimo.domain.BaseEntity;
import com.kumbh.mimo.domain.BaseTimeEntity;
import com.kumbh.mimo.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int point;

    private String imageUrl;

    private String author;

    @ManyToOne
    private Item item;

    @Builder
    public Posts(String content, int point, String imageUrl, String author, Item item) {
        this.content = content;
        this.point = point;
        this.imageUrl = imageUrl;
        this.author = author;
        this.item = item;
    }

    @Builder
    public Posts(String content, int point, String author, Item item) {
        this.content = content;
        this.point = point;
        this.author = author;
        this.item = item;
    }

    public void update(String content) {
        this.content = content;
    }

}
