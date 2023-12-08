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

// gestire le richieste HTTP e di restituire una risposta appropriata
@Controller
//Specifica il prefisso per tutte le richieste mappate 
@RequestMapping("/api")
//specificare quali domini possono accedere alle risorse fornite dal controller.
@CrossOrigin
public class PurchaseController {
	//utilizzata per l'iniezione di dipendenze automatica
    //riduce configurazioni manuali
	@Autowired
	private PurchaseService service;
	//gestirà le richieste HTTP GET indirizzate a "/api/info"
	@GetMapping("/info")
        public String apiInfo() {
		// costruire un oggetto JSON (apiInfo) che rappresenta informazioni sull'API, 
        // fornendo descrizioni per diverse operazioni. Infine, viene restituito un oggetto JSON in formato stringa.
        JSONObject apiInfo = new JSONObject();
		//  documentando le API di un'applicazione
		apiInfo.put("GET /purchases/{userId}", "Restituisce gli acquisti dell'utente specificato.");
		apiInfo.put("GET /purchases/purchase/{purchaseId}", "Restituisce un acquisto specifico.");
		apiInfo.put("POST /purchases/{userId}", "Effettua un acquisto per l'utente specificato.");
		//La stringa JSON risultante contiene le informazioni sull'API con descrizioni associate a ciascun endpoint specificato. 
		return apiInfo.toString();
	}


	// esempio di controller Spring MVC che gestisce richieste relative alle operazioni di acquisto
	// gestisce una richiesta GET per ottenere la lista di acquisti di un utente specificato.
	@GetMapping("/purchases/{userId}")
	//PathVariableestrae id dal url
	// @ResponseBody converte automaticamente in JSON la lista di prodotti (restituita dal servizio) direttamente nella risposta HTTP come corpo della risposta
	// pagable paginazione dei risultati
	public @ResponseBody Page<Purchase> listPurchases(@PathVariable String userId, Pageable pageRequest) {
		//restituisce un oggetto
		return service.getUserPurchases(userId, pageRequest);
	}

	@GetMapping("/purchases/purchase/{purchaseId}")
	//PathVariableestrae id dal url
	public @ResponseBody Purchase getPurchase(@PathVariable String purchaseId) {
		//restituisce un oggetto
		return service.getUserPurchase(purchaseId);
	}

	@PostMapping("/purchases/{userId}")
	// @RequestBody PurchaseRequest request estrae i dettagli dell'acquisto dalla richiesta HTTP.
	public @ResponseBody Purchase buy(@PathVariable String userId, @RequestBody PurchaseRequest request) {
		//restituisce un oggetto
		return service.buy(userId, request);
	}

}
