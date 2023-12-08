package com.fbk.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbk.users.domain.User;
import com.fbk.users.repository.UserRepository;

// dichiarare che questa classe è un componente di servizio
@Service
public class UserService {
    //utilizzata per l'iniezione di dipendenze,
    //un'istanza di UserRepository sarà automaticamente fornita al momento della creazione di un'istanza di UserService.
    @Autowired
    private UserRepository repo;
    
    // Cerca un utente per ID utilizzando il metodo findById di UserRepository.
    public User getUser(String id) {
        // Se l'utente viene trovato, viene restituito; altrimenti, viene restituito null.
        return repo.findById(Integer.parseInt(id)).orElse(null);
    }
    
    // Ottiene tutti gli utenti utilizzando il metodo findAll di UserRepository.
    public List<User> getAllUsers() {
        // ritorna un oggetto con tuti gli elementi
        return repo.findAll();
    }

    // Salva un singolo utente utilizzando il metodo save di UserRepository.
    public User saveUser(User user) {
        //restituisce l'oggetto User dopo che è stato salvato nel database.
        return repo.save(user);
    }

    // Salva una lista di utenti utilizzando il metodo saveAll di UserRepository.
    public List<User> saveUsers(List<User> users) {
        //restituisce la lista degli oggetti User dopo che sono stati salvati nel database 
        return repo.saveAll(users);
    }
        
}
