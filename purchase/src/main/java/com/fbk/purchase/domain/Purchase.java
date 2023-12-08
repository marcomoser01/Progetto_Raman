package com.fbk.purchase.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;

//Questo codice Java definisce una classe chiamata Product, che sembra essere una rappresentazione di un'entità in un database. 
// Le annotazioni utilizzate nel codice sono tipicamente associate a Java Persistence API (JPA), una specifica Java per la gestione 
// dei dati relazionali nelle applicazioni Java.
@Entity//Questa annotazione indica che la classe è un'entità JPA,classe mappata a un DB 
@AllArgsConstructor//crea un costruttore che accetta tutti i campi come parametri.
@NoArgsConstructor//genera un costruttore senza argomenti per la classe.
// @EnableAutoConfiguration
@Table(name = "Purchase")
public class Purchase {

	@Id//PK
	@GeneratedValue//generato automaticamente
	private Integer id;
	private String productId, productTitle, productCategory, userId;
	private Double price;
	private Integer quantity;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
