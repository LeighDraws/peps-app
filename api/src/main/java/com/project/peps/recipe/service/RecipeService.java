package com.project.peps.recipe.service;

import java.util.List;

import com.project.peps.recipe.model.Recipe;

public interface RecipeService {

    List<Recipe> findAllRecipes();

    Recipe findRecipeById(Long id);

    Recipe save(Recipe recipe);

    Recipe deleteById(Long id);
}