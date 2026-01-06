package com.project.peps.menuhasrecipe.controller;

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

import com.project.peps.menuhasrecipe.dto.MenuHasRecipeRequest;
import com.project.peps.menuhasrecipe.dto.MenuHasRecipeResponse;
import com.project.peps.menuhasrecipe.mapper.MenuHasRecipeMapper;
import com.project.peps.menuhasrecipe.model.MenuHasRecipe;
import com.project.peps.menuhasrecipe.service.MenuHasRecipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/menu-recipes")
@CrossOrigin(origins = "*")
public class MenuHasRecipeController {

    private final MenuHasRecipeService menuHasRecipeService;
    private final MenuHasRecipeMapper menuHasRecipeMapper;

    public MenuHasRecipeController(MenuHasRecipeService menuHasRecipeService, MenuHasRecipeMapper menuHasRecipeMapper) {
        this.menuHasRecipeService = menuHasRecipeService;
        this.menuHasRecipeMapper = menuHasRecipeMapper;
    }

    @PostMapping
    public ResponseEntity<MenuHasRecipeResponse> addRecipeToMenu(@Valid @RequestBody MenuHasRecipeRequest request) {
        MenuHasRecipe menuHasRecipe = menuHasRecipeService.addRecipeToMenu(request.getMenuId(), request.getRecipeId());
        return new ResponseEntity<>(menuHasRecipeMapper.toResponse(menuHasRecipe), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRecipeFromMenu(@PathVariable Long id) {
        menuHasRecipeService.removeRecipeFromMenu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<MenuHasRecipeResponse>> getRecipesByMenu(@PathVariable Long menuId) {
        List<MenuHasRecipe> recipes = menuHasRecipeService.findRecipesByMenuId(menuId);
        return ResponseEntity.ok(menuHasRecipeMapper.toResponseList(recipes));
    }
}
