package com.project.peps.recipe.service;

import com.project.peps.recipe.model.Recipe;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface RecipeService {

    List<Recipe> findAll();

    List<Recipe> findAll(Specification<Recipe> spec);

    Recipe findRecipeById(Long id);

    Recipe save(Recipe recipe);

    Recipe deleteById(Long id);
}