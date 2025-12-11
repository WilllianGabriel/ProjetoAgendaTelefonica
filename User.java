package com.willian.agendatelefonicaspringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    // Construtor vazio necessário para JPA
    public User() {}

    // Metodo Construtor com parametros de nome, email e senha do usuário
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // (Getter)
    public Long getId() {
        return id;
    }

    // (Setter)
    public void setId(Long id){
        this.id = id;
    }

    // (Getter)
    public String getName() {
        return name;
    }

    // (Setter)
    public void setName(String name){
        this.name = name;
    }
    // (Getter)
    public String getEmail() {
        return email;
    }

    // (Setter)
    public void setEmail(String email){
        this.email = email;
    }
    // (Getter)
    public String getPassword() {
        return password;
    }

    // (Setter)
    public void setPassword (String password){
        this.password = password;
    }
}
