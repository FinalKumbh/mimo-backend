package com.kumbh.mimo.domain.review;

import com.kumbh.mimo.domain.BaseEntity;
import com.kumbh.mimo.domain.item.Item;
import com.kumbh.mimo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int point;

    private String imageUrl;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Review(String content, int point, String imageUrl, User user, Item item) {
        this.content = content;
        this.point = point;
        this.imageUrl = imageUrl;
        this.user = user;
        this.item = item;
    }

    @Builder
    public Review(String content, int point, User user, Item item) {
        this.content = content;
        this.point = point;
        this.user = user;
        this.item = item;
    }

    public void update(String content) {
        this.content = content;
    }

}
