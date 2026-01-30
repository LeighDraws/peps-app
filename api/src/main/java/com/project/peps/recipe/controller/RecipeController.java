package com.project.peps.recipe.controller;

import com.project.peps.recipe.dto.RecipeFilterDTO;
import com.project.peps.recipe.dto.RecipeRequest;
import com.project.peps.recipe.dto.RecipeResponse;
import com.project.peps.recipe.mapper.RecipeMapper;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.repository.RecipeSpecification;
import com.project.peps.recipe.service.RecipeService;
import com.project.peps.user.service.UserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper, UserService userService) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAllRecipes(@ModelAttribute RecipeFilterDTO filter) {
        Specification<Recipe> spec = RecipeSpecification.filterBy(filter);
        List<Recipe> recipes = recipeService.findAll(spec);
        return ResponseEntity.ok(recipeMapper.toRecipeResponseList(recipes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.findRecipeById(id);
        return ResponseEntity.ok(recipeMapper.toRecipeResponse(recipe));
    }

    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest recipeRequest) {
        Recipe recipe = recipeMapper.toRecipe(recipeRequest);
        Recipe createdRecipe = recipeService.save(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeMapper.toRecipeResponse(createdRecipe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable Long id, @RequestBody RecipeRequest recipeRequest) {
        Recipe existingRecipe = recipeService.findRecipeById(id);

        recipeMapper.updateRecipeFromRequest(recipeRequest, existingRecipe);

        Recipe savedRecipe = recipeService.save(existingRecipe);

        return ResponseEntity.ok(recipeMapper.toRecipeResponse(savedRecipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}