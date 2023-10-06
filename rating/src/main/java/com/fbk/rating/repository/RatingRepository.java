package com.fbk.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.rating.domain.Rating;


public interface RatingRepository extends JpaRepository<Rating, String> {

	List<Rating> findByUserId(Integer userId);
	List<Rating> findByProductId(Integer productId);
}
