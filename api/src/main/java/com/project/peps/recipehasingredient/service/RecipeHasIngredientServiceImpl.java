package com.project.peps.recipehasingredient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.ingredient.model.Ingredient;
import com.project.peps.ingredient.repository.IngredientRepository;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.service.RecipeService;
import com.project.peps.recipehasingredient.dto.RecipeHasIngredientRequest;
import com.project.peps.recipehasingredient.model.RecipeHasIngredient;
import com.project.peps.recipehasingredient.repository.RecipeHasIngredientRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;

@Service
public class RecipeHasIngredientServiceImpl implements RecipeHasIngredientService {

    private final RecipeHasIngredientRepository recipeHasIngredientRepository;
    private final RecipeService recipeService;
    private final IngredientRepository ingredientRepository;

    public RecipeHasIngredientServiceImpl(RecipeHasIngredientRepository recipeHasIngredientRepository,
                                          RecipeService recipeService,
                                          IngredientRepository ingredientRepository) {
        this.recipeHasIngredientRepository = recipeHasIngredientRepository;
        this.recipeService = recipeService;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public RecipeHasIngredient addIngredientToRecipe(RecipeHasIngredientRequest request) {
        // Check if ingredient already exists in recipe
        if (recipeHasIngredientRepository.existsByRecipeIdAndIngredientId(request.getRecipeId(), request.getIngredientId())) {
            throw new IllegalArgumentException("Ingredient is already in the recipe");
        }

        Recipe recipe = recipeService.findRecipeById(request.getRecipeId());
        Ingredient ingredient = ingredientRepository.findById(request.getIngredientId())
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient", "id", request.getIngredientId()));

        RecipeHasIngredient recipeHasIngredient = RecipeHasIngredient.builder()
                .recipe(recipe)
                .ingredient(ingredient)
                .quantity(request.getQuantity())
                .measurementUnit(request.getMeasurementUnit())
                .build();

        return recipeHasIngredientRepository.save(recipeHasIngredient);
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        if (!recipeHasIngredientRepository.existsById(id)) {
            throw new ResourceNotFoundException("RecipeHasIngredient", "id", id);
        }
        recipeHasIngredientRepository.deleteById(id);
    }

    @Override
    public List<RecipeHasIngredient> getIngredientsByRecipeId(Long recipeId) {
        return recipeHasIngredientRepository.findByRecipeId(recipeId);
    }
}
