package com.willian.agendatelefonicaspringboot.service;

import com.willian.agendatelefonicaspringboot.entity.Contacts;
import com.willian.agendatelefonicaspringboot.repository.ContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactsService {

    private final ContactsRepository contactsRepository;

    public ContactsService(ContactsRepository contactsRepository){
        this.contactsRepository = contactsRepository;
    }

    // Criar Contato
    public Contacts createContact(Contacts contact){
        return contactsRepository.save(contact);
    }

    // Listar todos os contatos
    public List<Contacts> getAllContacts (){
        return contactsRepository.findAll();
    }

    // Buscar contato por ID
    public Optional<Contacts> getContactById(Long id){
        return contactsRepository.findById(id);
    }

    // Atualizar contato
    public Contacts updateContact(Contacts contact){
        return contactsRepository.save(contact);
    }

    // Deletar contato
    public void deleteContact(Long id){
        contactsRepository.deleteById(id);
    }
}