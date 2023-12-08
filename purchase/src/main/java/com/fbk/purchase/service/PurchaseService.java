package com.fbk.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fbk.purchase.domain.Product;
import com.fbk.purchase.domain.Purchase;
import com.fbk.purchase.domain.PurchaseRequest;
import com.fbk.purchase.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository repo;
	
	@Autowired
	private ProductService productService;
	// Restituisce una pagina di acquisti associati a un utente specificato.
	// Restituirà una pagina di oggetti Purchase e richiede l'ID dell'utente e i parametri di paginazione come argomenti.
	public Page<Purchase> getUserPurchases(String userId, Pageable pageRequest) {
		// findByUserId del PurchaseRepository ottiene una pagina di acquisti in base all'ID dell'utente fornito e ai parametri di paginazione specificati (pageRequest).
		return repo.findByUserId(Integer.parseInt(userId), pageRequest);
	}
	// restituirà un oggetto Purchase e richiede l'ID dell'acquisto come argomento.
	public Purchase getUserPurchase(String purchaseId) {
		// Recupera un singolo acquisto in base all'ID dell'acquisto fornito
		// Integer.parseInt(purchaseId) converte l'ID dell'acquisto da una String a un Integer, se necessario.
		// orelse Se l'acquisto con l'ID specificato viene trovato, viene restituito; in caso contrario, viene restituito null
		return repo.findById(Integer.parseInt(purchaseId)).orElse(null);
	}
	//gestisce il processo di acquisto, creando un nuovo oggetto Purchase, associandolo a un prodotto esistente e registrando l'acquisto nel repository (repo)
	public Purchase buy(String userId, PurchaseRequest request) {
		//Crea un nuovo oggetto Purchase, che rappresenterà l'acquisto.
		Purchase purchase = new Purchase();
		// Utilizza il servizio dei prodotti (productService) per ottenere informazioni sul prodotto associato all'ID nella richiesta
		Product product = productService.getProduct(request.getProductId());
		//Verifica se il prodotto esiste
		if (product != null) {
			//vengono impostati i dettagli dell'acquisto utilizzando le informazioni ottenute dal prodotto
			//imposta idproduct,quantita ,id user ecc.
			purchase.setProductId(request.getProductId());
			purchase.setQuantity(request.getCount());
			purchase.setUserId(userId);
			purchase.setProductTitle(product.getTitle());
			purchase.setProductCategory(product.getCategory());
			purchase.setPrice(product.getPrice());
			//Utilizza il servizio dei prodotti (productService) per aggiornare la disponibilità del prodotto sottraendo la quantità acquistata (request.getCount()).
			productService.bookAvailability(request.getProductId(), request.getCount());
			// Salva l'oggetto Purchase nel repository delle acquisti (repo) utilizzando il metodo save del PurchaseRepository.
			return repo.save(purchase);			
		}
		
		return purchase;
	}
}
