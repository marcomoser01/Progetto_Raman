package com.fbk.purchase.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.fbk.purchase.domain.Purchase;
import com.fbk.purchase.domain.PurchaseRequest;
import com.fbk.purchase.service.PurchaseService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PurchaseController {

	@Autowired
	private PurchaseService service;

	@GetMapping("/info")
    public String apiInfo() {
        JSONObject apiInfo = new JSONObject();

        apiInfo.put("GET /users", "Restituisce la lista di tutti gli utenti.");
        apiInfo.put("GET /user/{id}", "Restituisce un utente specifico in base all'ID.");
        apiInfo.put("POST /addUser", "Aggiunge un nuovo utente.");
        apiInfo.put("POST /addUsers", "Aggiunge una lista di nuovi utenti.");

        return apiInfo.toString();
    }

	@GetMapping("/purchases/{userId}")
	public @ResponseBody Page<Purchase> listPurchases(@PathVariable String userId, Pageable pageRequest) {
		return service.getUserPurchases(userId, pageRequest);
	}

	@GetMapping("/purchases/purchase/{purchaseId}")
	public @ResponseBody Purchase getPurchase(@PathVariable String purchaseId) {
		return service.getUserPurchase(purchaseId);
	}

	@PostMapping("/purchases/{userId}")
	public @ResponseBody Purchase buy(@PathVariable String userId, @RequestBody PurchaseRequest request) {
		return service.buy(userId, request);
	}

}
