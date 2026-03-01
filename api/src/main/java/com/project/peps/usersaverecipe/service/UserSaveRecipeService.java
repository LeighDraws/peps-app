package com.project.peps.usersaverecipe.service;

import java.util.List;

import com.project.peps.usersaverecipe.model.UserSaveRecipe;

public interface UserSaveRecipeService {
    
    UserSaveRecipe saveRecipe(Long recipeId, String userEmail);
    
    void unsaveRecipe(Long recipeId, String userEmail);
    
    List<UserSaveRecipe> getSavedRecipesByUserId(Long userId);
}
