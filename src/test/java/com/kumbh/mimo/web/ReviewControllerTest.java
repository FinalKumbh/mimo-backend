package com.kumbh.mimo.web;



import com.kumbh.mimo.domain.review.Review;
import com.kumbh.mimo.domain.review.ReviewRepository;

import com.kumbh.mimo.dto.review.ReviewSaveRequestDto;
import com.kumbh.mimo.dto.review.ReviewUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WebApplicationContext context;

    @AfterEach
    public void tearDown() throws Exception{
        //postsRepository.deleteAll();
    }

//    @Test
//    public void Posts_등록된다() throws Exception{
//        // given
//        String title = "title";
//        String content = "content";
//        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .author("author")
//                .build();
//
//        String url = "http://localhost:"+ port + "/api/v1/posts";
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        //then
//
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Review> all = reviewRepository.findAll();
////        assertThat(all.get(0).getTitle()).isEqualTo(title);
////        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//    @Test
//    public void Posts_수정된다() throws Exception{
//        // given
//        Review savedReview = reviewRepository.save(Review.builder()
//                .content("content")
//                .author("author")
//                .build());
//
//        Long updateId = savedReview.getId();
//        String expectedTitle = "title2";
//        String expectedContent = "content2";
//
//        ReviewUpdateRequestDto requestDto = ReviewUpdateRequestDto.builder()
//                .title(expectedTitle)
//                .content(expectedContent)
//                .build();
//
//        String url = "http://localhost:"+ port + "/api/v1/posts/" + updateId;
//
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        //then
//
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Review> all = reviewRepository.findAll();
////        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
////        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//    }

}