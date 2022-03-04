package com.kumbh.mimo.domain.review;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @AfterEach
    public void cleanup(){
        reviewRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        reviewRepository.save(Review.builder()
                .content(content)
                .author("chaehoon.gwak@gmail.com")
                .build());

        // when
        List<Review> reviewList = reviewRepository.findAll();

        //then
        Review review = reviewList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        // given
        LocalDateTime now = LocalDateTime.of(2022,1,29,0,0,0);
        reviewRepository.save(Review.builder()
                .content("content")
                .author("author")
                .build());

        //when
        List<Review> reviewList = reviewRepository.findAll();

        //then
        Review review = reviewList.get(0);

        System.out.println(">>>>>>>>> createDate="+review.getCreatedDate()+
                ", modifiedDate="+review.getModifiedDate());

        assertThat(review.getCreatedDate()).isAfter(now);
        assertThat(review.getModifiedDate()).isAfter(now);
    }
}