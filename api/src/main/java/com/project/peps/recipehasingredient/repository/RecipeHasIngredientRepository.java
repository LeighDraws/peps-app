package com.project.peps.recipehasingredient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.peps.recipehasingredient.model.RecipeHasIngredient;

public interface RecipeHasIngredientRepository extends JpaRepository<RecipeHasIngredient, Long> {

    List<RecipeHasIngredient> findByRecipeId(Long recipeId);
    
    // Pour verifier si un ingredient est deja dans une recette
    boolean existsByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
