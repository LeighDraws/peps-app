package com.project.peps.ingredient.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.peps.ingredient.dto.IngredientRequest;
import com.project.peps.ingredient.dto.IngredientResponse;
import com.project.peps.ingredient.mapper.IngredientMapper;
import com.project.peps.ingredient.model.Ingredient;
import com.project.peps.ingredient.service.IngredientService;
import com.project.peps.shared.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponse>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.findAll();
        List<IngredientResponse> ingredientResponses = ingredients.stream()
                .map(ingredientMapper::toIngredientResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ingredientResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with id " + id + " not found"));
        return ResponseEntity.ok(ingredientMapper.toIngredientResponse(ingredient));
    }

    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@Valid @RequestBody IngredientRequest ingredientRequest) {
        Ingredient ingredient = ingredientMapper.toIngredient(ingredientRequest);
        Ingredient savedIngredient = ingredientService.save(ingredient);
        return new ResponseEntity<>(ingredientMapper.toIngredientResponse(savedIngredient), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponse> updateIngredient(@PathVariable Long id, @Valid @RequestBody IngredientRequest ingredientRequest) {
        Ingredient ingredient = ingredientService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with id " + id + " not found"));

        ingredientMapper.updateIngredientFromRequest(ingredientRequest, ingredient);
        Ingredient updatedIngredient = ingredientService.update(ingredient);

        return ResponseEntity.ok(ingredientMapper.toIngredientResponse(updatedIngredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientById(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
