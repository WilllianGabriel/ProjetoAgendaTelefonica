package com.willian.agendatelefonicaspringboot.repository;

import com.willian.agendatelefonicaspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
}
