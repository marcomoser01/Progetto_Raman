package com.fbk.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.users.domain.User;

//estende l'interfaccia JpaRepository, progettata per l'accesso ai dati per l'entità User
public interface UserRepository extends JpaRepository<User, Integer> {
    // Optional il risultato potrebbe essere vuoto 
    // basata sul nome del metodo: findById
    Optional<User> findById(Integer id);

}
