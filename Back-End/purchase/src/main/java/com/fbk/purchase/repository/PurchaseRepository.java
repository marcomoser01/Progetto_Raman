package com.fbk.purchase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.purchase.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	Page<Purchase> findByUserId(String userId, Pageable page);
}
