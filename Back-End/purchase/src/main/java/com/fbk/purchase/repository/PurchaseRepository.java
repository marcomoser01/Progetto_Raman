package com.fbk.purchase.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.purchase.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	Optional<List<Purchase>> findByUserId(String userId);
}
