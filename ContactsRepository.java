package com.willian.agendatelefonicaspringboot.repository;

import com.willian.agendatelefonicaspringboot.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
}