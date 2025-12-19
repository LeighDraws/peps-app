package com.project.peps.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.mapper.UserMapper;
import com.project.peps.user.model.User;
import com.project.peps.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    // Pas de Autowired car on utilise le constructeur qui est recommandé pour l'injection de dépendances
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(userMapper.toResponseList(users));
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    // Create new user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User savedUser = userService.save(user);
        return new ResponseEntity<>(userMapper.toResponse(savedUser), HttpStatus.CREATED);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        // 1. Récupérer l'existant (C'est le Service qui fait le "find")
        User existingUser = userService.findUserById(id);

        // 2. Mettre à jour les champs de l'entité avec ceux du DTO (C'est le Mapper qui travaille)
        userMapper.updateUserFromRequest(userRequest, existingUser);

        // 3. Sauvegarder l'entité mise à jour (C'est le Service qui sauvegarde)
        User savedUser = userService.save(existingUser);

        // 4. Renvoyer la réponse mappée
        return ResponseEntity.ok(userMapper.toResponse(savedUser));
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        User deletedUser = userService.deleteById(id);
        return ResponseEntity.ok(userMapper.toResponse(deletedUser));
    }
}
