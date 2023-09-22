package com.fbk.purchase.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fbk.purchase.domain.Purchase;
import com.fbk.purchase.domain.PurchaseRequest;
import com.fbk.purchase.service.PurchaseService;

@Controller
public class PurchaseController {

	@Autowired
	private PurchaseService service;
	
	@GetMapping("/api/purchases/{userId}")
	public @ResponseBody Page<Purchase> listPurchases(@PathVariable String userId, Pageable pageRequest) {
		return service.getUserPurchases(userId, pageRequest);
	}
	
	@GetMapping("/api/purchases/purchase/{purchaseId}")
	public @ResponseBody Purchase getPurchase(@PathVariable String purchaseId) {
		return service.getUserPurchase(purchaseId);
	}
	
	@PostMapping("/api/purchases/{userId}")
	public @ResponseBody Purchase buy(@PathVariable String userId, @RequestBody PurchaseRequest request) {
		return service.buy(userId, request);
	}

}
