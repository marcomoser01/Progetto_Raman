package com.fbk.rating.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;

//una classe che è mappata a una tabella nel database relazionale.
@Entity 
// generano automaticamente un costruttore con tutti i parametri
@AllArgsConstructor
//e un costruttore senza argomenti 
@NoArgsConstructor
@Table(name = "User")//nome tabella

public class User {
    @Id//PF
    @GeneratedValue//genara il valore 
    private Integer id;
    private String Name;
    
    // metodi setter e getter (per accedere e modificare i campi (variabili) di una classe)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    

}
