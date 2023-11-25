package com.fbk.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbk.users.domain.User;
import com.fbk.users.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;
    
    public User getUser(String id) {
        return repo.findById(Integer.parseInt(id)).orElse(null);
    }
    
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return repo.saveAll(users);
    }
        
}
