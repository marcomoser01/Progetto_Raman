package com.fbk.rating.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.rating.domain.Rating;


public interface RatingRepository extends JpaRepository<Rating, String> {

	Page<Rating> findByUserId(Integer userId, Pageable pageRequest);
	Page<Rating> findByProductId(Integer productId, Pageable pageRequest);
}
