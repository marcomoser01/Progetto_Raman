package com.fbk.rating.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {
    private Integer vote;
    private String message;
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
