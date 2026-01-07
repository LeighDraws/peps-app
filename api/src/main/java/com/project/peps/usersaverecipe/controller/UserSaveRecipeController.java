package com.project.peps.usersaverecipe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.peps.usersaverecipe.dto.UserSaveRecipeRequest;
import com.project.peps.usersaverecipe.dto.UserSaveRecipeResponse;
import com.project.peps.usersaverecipe.mapper.UserSaveRecipeMapper;
import com.project.peps.usersaverecipe.model.UserSaveRecipe;
import com.project.peps.usersaverecipe.service.UserSaveRecipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-saved-recipes")
@CrossOrigin(origins = "*")
public class UserSaveRecipeController {

    private final UserSaveRecipeService userSaveRecipeService;
    private final UserSaveRecipeMapper userSaveRecipeMapper;

    public UserSaveRecipeController(UserSaveRecipeService userSaveRecipeService,
                                    UserSaveRecipeMapper userSaveRecipeMapper) {
        this.userSaveRecipeService = userSaveRecipeService;
        this.userSaveRecipeMapper = userSaveRecipeMapper;
    }

    @PostMapping
    public ResponseEntity<UserSaveRecipeResponse> saveRecipe(@Valid @RequestBody UserSaveRecipeRequest request) {
        UserSaveRecipe savedEntity = userSaveRecipeService.saveRecipe(request);
        return new ResponseEntity<>(userSaveRecipeMapper.toResponse(savedEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> unsaveRecipe(@PathVariable Long id) {
        userSaveRecipeService.unsaveRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSaveRecipeResponse>> getSavedRecipesByUser(@PathVariable Long userId) {
        List<UserSaveRecipe> list = userSaveRecipeService.getSavedRecipesByUserId(userId);
        return ResponseEntity.ok(userSaveRecipeMapper.toResponseList(list));
    }
}
