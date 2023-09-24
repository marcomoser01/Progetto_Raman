package com.fbk.rating.domain;

public class RatingRequest {
    private String product_id;
    private String user_id;
    private Integer vote;
    private String message;
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
