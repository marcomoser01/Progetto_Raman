package com.fbk.rating.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.rating.domain.Rating;


public interface RatingRepository extends JpaRepository<Rating, Integer> {

	Optional<Rating> findById(Integer id);
	List<Rating> findByUserId(Integer userId);
	List<Rating> findByProductId(Integer productId);
	Rating findByProductIdAndUserId(Integer productId, Integer userId);

}
