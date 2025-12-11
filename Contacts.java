package com.willian.agendatelefonicaspringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    // Construtor vazio necessário para JPA
    public Contacts() {}

    // Método Construtor com parametros do nome e telefone dos contatos
    public Contacts(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // (Getter)
    public Long getId() {
        return id;
    }

    // (Setter)
    public void setId(Long id) {
        this.id = id;
    }

    // (Getter)
    public String getName() {
        return name;
    }

    // (Setter)
    public void setName(String name) {
        this.name = name;
    }

    // (getter)
    public String getPhone() {
        return phone;
    }

    // (Setter)
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Método para mostrar o ID, Nome e Telefone
    public String toString() {
        return "\nID: " + id + "\nName: " + name + phone;
    }
}