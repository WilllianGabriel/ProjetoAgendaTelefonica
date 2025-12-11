package com.willian.agendatelefonicaspringboot.controller;

import com.willian.agendatelefonicaspringboot.entity.Contacts;
import com.willian.agendatelefonicaspringboot.service.ContactsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private final ContactsService contactsService;

    public ContactsController(ContactsService contactsService){
        this.contactsService = contactsService;
    }

    // Criar um novo contato
    @PostMapping
    public Contacts createContact(@RequestBody Contacts contact) {
        return contactsService.createContact(contact);
    }

    // Listar todos os contatos
    @GetMapping
    public List<Contacts> getAllContacts(){
        return contactsService.getAllContacts();
    }

    // Buscar contato por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contacts> getContactById (@PathVariable Long id){
        return contactsService.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar contato
    @PutMapping("/{id}")
    public ResponseEntity<Contacts> updateContact (@PathVariable Long id, @RequestBody Contacts contact){
        return contactsService.getContactById(id)
                .map(existingContact ->{
                    existingContact.setName(contact.getName());
                    existingContact.setPhone(contact.getPhone());
                    Contacts updated = contactsService.updateContact(existingContact);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete contato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id){
        if(contactsService.getContactById(id).isPresent()){
            contactsService.deleteContact(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
