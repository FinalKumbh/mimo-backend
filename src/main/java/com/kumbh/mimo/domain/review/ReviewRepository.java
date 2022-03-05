package com.kumbh.mimo.domain.review;

import com.kumbh.mimo.dto.review.ReviewResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Review findByUserIdAndItemId(Long userId, Long itemId);

//    @Query("SELECT r FROM Review r WHERE r.id=:itemId ORDER BY r.createdDate DESC")
//    List<ReviewResponseDto> findAllDesc(@Param("itemId") Long itemId);

//    @Query("SELECT new com.kumbh.mimo.dto.review.ReviewResponseDto(r.reviewId, r.content, u.name ,r.point, r.imageUrl) " +
//            "FROM review r INNER JOIN users u " +
//            "ON r.userId = u.id " +
//            "WHERE r.itemId = :itemId " +
//            "ORDER BY r.createdDate DESC")
//    List<ReviewResponseDto> findAllDesc(@Param("itemId") Long itemId);

    @Query("select new com.kumbh.mimo.dto.review.ReviewResponseDto(r.id, r.content, r.user.name ,r.point, r.imageUrl) " +
            "from Review r " +
            "where r.item.id = :itemId " +
            "order by r.createdDate DESC")
    List<ReviewResponseDto> findAllDesc(@Param("itemId") Long itemId);

}
