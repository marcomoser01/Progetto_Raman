package com.fbk.users.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/info")
    public Map<String, String> apiInfo() {
        Map<String, String> info = new HashMap<>();

        info.put("GET /users", "Restituisce la lista di tutti gli utenti.");
        info.put("GET /user/{id}", "Restituisce un utente specifico in base all'ID.");
        info.put("POST /addUser", "Aggiunge un nuovo utente.");
        info.put("POST /addUsers", "Aggiunge una lista di nuovi utenti.");

        return info;
    }

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
