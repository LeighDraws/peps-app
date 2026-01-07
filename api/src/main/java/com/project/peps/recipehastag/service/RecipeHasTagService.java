package com.project.peps.recipehastag.service;

import java.util.List;

import com.project.peps.recipehastag.dto.RecipeHasTagRequest;
import com.project.peps.recipehastag.model.RecipeHasTag;

public interface RecipeHasTagService {
    
    RecipeHasTag addTagToRecipe(RecipeHasTagRequest request);
    
    void removeTagFromRecipe(Long id);
    
    List<RecipeHasTag> getTagsByRecipeId(Long recipeId);
}
