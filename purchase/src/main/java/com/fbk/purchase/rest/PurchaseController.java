package com.fbk.purchase.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fbk.purchase.domain.Purchase;
import com.fbk.purchase.domain.PurchaseRequest;
import com.fbk.purchase.service.PurchaseService;

@Controller
@RequestMapping("/api")
@CrossOrigin
public class PurchaseController {

	@Autowired
	private PurchaseService service;

	@GetMapping("/info")
    public String apiInfo() {
        JSONObject apiInfo = new JSONObject();

		apiInfo.put("GET /purchases/{userId}", "Restituisce gli acquisti dell'utente specificato.");
		apiInfo.put("GET /purchases/purchase/{purchaseId}", "Restituisce un acquisto specifico.");
		apiInfo.put("POST /purchases/{userId}", "Effettua un acquisto per l'utente specificato.");

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
