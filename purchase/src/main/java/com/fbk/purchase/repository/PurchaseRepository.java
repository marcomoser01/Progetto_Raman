package com.fbk.purchase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.purchase.domain.Purchase;

// estende la JpaRepository (fornisce metodi CRUD) 
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	// la query cerca nella tabella "Purchase" dove il campo userId (filtro query) corrisponde al valore fornito come argomento userId
	//Restituisce un oggetto di tipo Page<Purchase>
	Page<Purchase> findByUserId(Integer userId, Pageable pageRequest);
}
