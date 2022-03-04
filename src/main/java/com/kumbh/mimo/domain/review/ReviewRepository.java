package com.kumbh.mimo.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Review findByUserIdAndItemId(Long userId, Long itemId);

    @Query("SELECT r FROM Review r ORDER BY r.id DESC")
    List<Review> findAllDesc();
}
