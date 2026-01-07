package com.project.peps.recipehasingredient.controller;

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

import com.project.peps.recipehasingredient.dto.RecipeHasIngredientRequest;
import com.project.peps.recipehasingredient.dto.RecipeHasIngredientResponse;
import com.project.peps.recipehasingredient.mapper.RecipeHasIngredientMapper;
import com.project.peps.recipehasingredient.model.RecipeHasIngredient;
import com.project.peps.recipehasingredient.service.RecipeHasIngredientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recipe-ingredients")
@CrossOrigin(origins = "*")
public class RecipeHasIngredientController {

    private final RecipeHasIngredientService recipeHasIngredientService;
    private final RecipeHasIngredientMapper recipeHasIngredientMapper;

    public RecipeHasIngredientController(RecipeHasIngredientService recipeHasIngredientService,
                                         RecipeHasIngredientMapper recipeHasIngredientMapper) {
        this.recipeHasIngredientService = recipeHasIngredientService;
        this.recipeHasIngredientMapper = recipeHasIngredientMapper;
    }

    @PostMapping
    public ResponseEntity<RecipeHasIngredientResponse> addIngredientToRecipe(@Valid @RequestBody RecipeHasIngredientRequest request) {
        RecipeHasIngredient savedEntity = recipeHasIngredientService.addIngredientToRecipe(request);
        return new ResponseEntity<>(recipeHasIngredientMapper.toResponse(savedEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeIngredientFromRecipe(@PathVariable Long id) {
        recipeHasIngredientService.removeIngredientFromRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<RecipeHasIngredientResponse>> getIngredientsByRecipe(@PathVariable Long recipeId) {
        List<RecipeHasIngredient> list = recipeHasIngredientService.getIngredientsByRecipeId(recipeId);
        return ResponseEntity.ok(recipeHasIngredientMapper.toResponseList(list));
    }
}
