package com.kumbh.mimo.service;


import com.kumbh.mimo.domain.review.Review;
import com.kumbh.mimo.domain.review.ReviewRepository;
import com.kumbh.mimo.dto.review.ReviewListResponseDto;
import com.kumbh.mimo.dto.review.ReviewResponseDto;
import com.kumbh.mimo.dto.review.ReviewSaveRequestDto;
import com.kumbh.mimo.dto.review.ReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long save(ReviewSaveRequestDto requestDto){
        return reviewRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ReviewUpdateRequestDto requestDto){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        review.update(requestDto.getContent());

        return id;
    }

    @Transactional
    public ReviewResponseDto findById(Long id){
        Review entity = reviewRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("헤당 게시글이 없습니다. id="+id));
        return new ReviewResponseDto(entity);
    }

    @Transactional
    public void delete (Long id){
        Review review = reviewRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewListResponseDto> findAllDesc(){
        return reviewRepository.findAllDesc().stream()
                .map(ReviewListResponseDto::new)
                .collect(Collectors.toList());
    }

}