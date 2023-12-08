package com.fbk.rating.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;

//una classe che è mappata a una tabella nel database relazionale.
@Entity
// generano automaticamente un costruttore con tutti i parametri
@AllArgsConstructor
//e un costruttore senza argomenti 
@NoArgsConstructor
@Table(name = "Rating")//nome tabella 
public class Rating {
    @Id//PK
    @GeneratedValue//valore generato 
    private Integer id;
    private Integer vote;
    private String message;
    private Integer userId;
    private Integer productId;

    //metodi getter e setter (per accedere e modificare i campi (variabili) di una classe)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId = userId;
    }

    public void setuserId(String userId) {
        this.userId = Integer.parseInt(userId);
    }

    public Integer getproductId() {
        return productId;
    }

    public void setproductId(Integer productId) {
        this.productId = productId;
    }

    public void setproductId(String productId) {
        this.productId = Integer.parseInt(productId);
    }

}