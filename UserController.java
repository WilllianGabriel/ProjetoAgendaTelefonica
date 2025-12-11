package com.willian.agendatelefonicaspringboot.controller;

import com.willian.agendatelefonicaspringboot.entity.User;
import com.willian.agendatelefonicaspringboot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
            this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // listar todos os usuários
    @GetMapping
    public List<User> GetAllUsers(){
            return userService.getAllUsers();
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> GetUserById(@PathVariable Long id){
            return userService.getUserById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
            return userService.getUserById(id)
                    .map(existingUser -> {
                        existingUser.setName(user.getName());
                        existingUser.setEmail(user.getEmail());
                        existingUser.setPassword(user.getPassword());
                        User updated = userService.updateUser(existingUser);
                        return ResponseEntity.ok(updated);
                    })
                    .orElse(ResponseEntity.notFound().build());
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id){
            if (userService.getUserById(id).isPresent()){
                userService.deleteUser(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
    }
}
