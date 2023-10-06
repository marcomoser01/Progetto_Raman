package com.fbk.users.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fbk.users.domain.User;
import com.fbk.users.services.UserService;


@RestController()
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    
    @Autowired
    private UserService service;
    
    @GetMapping("/users")
    public @ResponseBody List<User> listUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public @ResponseBody User getUser(@PathVariable String id) {
        return service.getUser(id);
    }
    
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return service.saveUsers(users);
    }
    
}
