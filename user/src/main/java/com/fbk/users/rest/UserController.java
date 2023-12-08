package com.fbk.users.rest;

import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fbk.users.domain.User;
import com.fbk.users.services.UserService;

//gestire le richieste HTTP e restituire le risposte come dati JSON (o XML) in base ai casi d'uso.
@RestController
//Specifica il prefisso per tutte le richieste mappate
@RequestMapping("/api")
//specificare quali domini possono accedere alle risorse fornite dal controller.
@CrossOrigin
public class UserController {
    //utilizzata per l'iniezione di dipendenze automatica
    //riduce configurazioni manuali
    @Autowired
    //attributo
    private UserService service;
    //gestirà le richieste HTTP GET indirizzate a "/api/info"
    @GetMapping("/info")
    public String apiInfo() {
        // Crea un oggetto JSONObject per rappresentare le informazioni sull'API
        JSONObject apiInfo = new JSONObject();
        // restituire informazioni sulla tua API sotto forma di stringa JSON
        apiInfo.put("GET /users", "Restituisce la lista di tutti gli utenti.");
        apiInfo.put("GET /user/{id}", "Restituisce un utente specifico in base all'ID.");
        apiInfo.put("POST /addUser", "Aggiunge un nuovo utente.");
        apiInfo.put("POST /addUsers", "Aggiunge una lista di nuovi utenti.");
        // Restituisci le informazioni sull'API come stringa JSON
        return apiInfo.toString();
    }

    // Questo metodo gestisce una richiesta GET all'endpoint /users
    @GetMapping("/users")
    // ottenere una lista di tutti gli utenti 
    // @ResponseBody serializzare il risultato direttamente nella risposta HTTP come corpo della risposta.
    public @ResponseBody List<User> listUsers() {
        // restituisce lista di tutti gli utenti
        return service.getAllUsers();
    }
    // Questo metodo gestisce una richiesta GET all'endpoint /users/id
    @GetMapping("/user/{id}")
    // @ResponseBody serializzare il risultato direttamente nella risposta HTTP come corpo della risposta.
    //@PathVariable prende id dal URL
    public @ResponseBody User getUser(@PathVariable String id) {
        // Il metodo chiama il servizio per ottenere un utente specifico (getUser) in base all'ID  restituisce l'utente
        return service.getUser(id);
    }
    // Questo metodo gestisce una richiesta POST all'endpoint /addUser
    @PostMapping("/addUser")
    // @RequestBody estrae l'oggetto User dal corpo della richiesta.
    public User addUser(@RequestBody User user) {
        // restituisce l'utente salvato come corpo della risposta.
        return service.saveUser(user);
    }
    // Questo metodo gestisce una richiesta POST all'endpoint /addUsers
    @PostMapping("/addUsers")
    // @RequestBody estrae una lista di oggetti User dal corpo della richiesta.
    public List<User> addUsers(@RequestBody List<User> users) {
        // restituisce la lista salvata come corpo della risposta.
        return service.saveUsers(users);
    }

}
