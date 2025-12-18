package com.project.peps.ingredient.service;

import java.util.List;
import java.util.Optional;

import com.project.peps.ingredient.model.Ingredient;

public interface IngredientService {

    List<Ingredient> findAll();

    Optional<Ingredient> findById(Long id);

    Ingredient save(Ingredient ingredient);

    Ingredient update(Ingredient ingredient);
    
    void deleteById(Long id);
}