package com.project.peps.user.controller;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.mapper.UserMapper;
import com.project.peps.user.model.User;
import com.project.peps.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

  private final UserService userService;

  // Pas de Autowired car on utilise le constructeur qui est recommandé pour l'injection de dépendances
  public UserController(UserService userService) {
    this.userService = userService;
  }

  // Get all users
  @GetMapping
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    List<User> users = userService.findAll();
    return ResponseEntity.ok(UserMapper.toResponseList(users));
  }

  // Get user by ID
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(UserMapper.toResponse(user));
  }

  // Create new user
  @PostMapping
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
    User user = UserMapper.toEntity(userRequest);
    User savedUser = userService.save(user);
    return new ResponseEntity<>(UserMapper.toResponse(savedUser), HttpStatus.CREATED);
  }

  // Update user
  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> updateUser(
      @PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
    User updatedUser = userService.updateUser(id, userRequest);
    return ResponseEntity.ok(UserMapper.toResponse(updatedUser));
  }

  // Delete user
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}