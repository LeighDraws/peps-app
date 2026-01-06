package com.project.peps.recipehasingredient.service;

import java.util.List;

import com.project.peps.recipehasingredient.dto.RecipeHasIngredientRequest;
import com.project.peps.recipehasingredient.model.RecipeHasIngredient;

public interface RecipeHasIngredientService {

    RecipeHasIngredient addIngredientToRecipe(RecipeHasIngredientRequest request);

    void removeIngredientFromRecipe(Long id);

    List<RecipeHasIngredient> getIngredientsByRecipeId(Long recipeId);
}
