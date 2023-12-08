package com.fbk.users.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;

//mappata a una tabella nel database.
@Entity
// Genera automaticamente un costruttore con parametri che accetta tutti i campi della classe come argomenti.
@AllArgsConstructor
// Genera automaticamente un costruttore senza argomenti.
@NoArgsConstructor

@Table(name = "Users")//nome tabella
public class User {
    @Id//PK
    @GeneratedValue//genera automaticamente il valore
    private Integer id;

    @NotNull//no nullo
    private String name;
    @NotNull
    private String cognome;

    //setter e getter 
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    

}
