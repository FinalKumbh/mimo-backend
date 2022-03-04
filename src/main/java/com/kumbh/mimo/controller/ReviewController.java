package com.kumbh.mimo.controller;


import com.kumbh.mimo.dto.review.ReviewResponseDto;
import com.kumbh.mimo.dto.review.ReviewSaveRequestDto;
import com.kumbh.mimo.dto.review.ReviewUpdateRequestDto;
import com.kumbh.mimo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService; // 생성자로 주입

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto){
        return reviewService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto){
        return reviewService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public ReviewResponseDto findById (@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        reviewService.delete(id);
        return id;
    }
}
