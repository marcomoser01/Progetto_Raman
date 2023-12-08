package com.fbk.purchase.domain;
// rappresenta una richiesta di acquisto
public class PurchaseRequest {
	// identificativo prodotto e quantita
	private String productId;
	private Integer count;
	// metodi getter e setter per accedere e modificare i valori degli attributi.
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
