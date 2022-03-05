package com.kumbh.mimo.controller;


import com.kumbh.mimo.domain.review.Review;
import com.kumbh.mimo.dto.review.ReviewResponseDto;
import com.kumbh.mimo.dto.review.ReviewSaveRequestDto;
import com.kumbh.mimo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService; // 생성자로 주입

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReviewSaveRequestDto requestDto){
        boolean result = reviewService.save(requestDto);
        String resultMsg = "";

        if(!result) {
            resultMsg = "이미 리뷰가 등록 되었습니다.";
            return new ResponseEntity<String>(resultMsg, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Review>(HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getReviews(@PathVariable Long itemId){
        List<ReviewResponseDto> reviewList =  reviewService.getReviewList(itemId);

        return ResponseEntity.ok(reviewList);
    }

//    @DeleteMapping
//    public ResponseEntity<?> delete() {
//
//    }

//    @PutMapping("/api/v1/posts/{id}")
//    public Long update(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto){
//        return reviewService.update(id, requestDto);
//    }
//
//    @GetMapping("/api/v1/posts/{id}")
//    public ReviewResponseDto findById (@PathVariable Long id) {
//        return reviewService.findById(id);
//    }
//
//    @DeleteMapping("/api/v1/posts/{id}")
//    public Long delete(@PathVariable Long id){
//        reviewService.delete(id);
//        return id;
//    }
}
