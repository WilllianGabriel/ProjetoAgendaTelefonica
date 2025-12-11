package com.willian.agendatelefonicaspringboot.service;

import com.willian.agendatelefonicaspringboot.entity.User;
import com.willian.agendatelefonicaspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Cria o usuário
    public User createUser(User user){
        return userRepository.save(user);
    }

    // Lista todos os Usuários
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    // Atualizar usuário
    public User updateUser(User user){
        return userRepository.save(user);
    }

    // Deletar usuário
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}