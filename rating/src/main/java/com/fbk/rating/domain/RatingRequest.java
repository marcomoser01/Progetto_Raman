package com.fbk.rating.domain;

public class RatingRequest {
    //due attributi voto e messaggio 
    private Integer vote;
    private String message;
    //metodi getter e setter (per accedere e modificare i campi (variabili) di una classe)
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
    
}
