package com.fbk.rating.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fbk.rating.domain.User;

@Service
public class UserService {
    private String endpoint = "http://gateway:9999/users";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public User getUser(String user_Id) {
		return restTemplate.getForObject(endpoint + "/api/user/{userId}", User.class, user_Id);
	}
	
}
