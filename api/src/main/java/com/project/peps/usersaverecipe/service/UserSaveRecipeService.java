package com.project.peps.usersaverecipe.service;

import java.util.List;

import com.project.peps.usersaverecipe.dto.UserSaveRecipeRequest;
import com.project.peps.usersaverecipe.model.UserSaveRecipe;

public interface UserSaveRecipeService {
    
    UserSaveRecipe saveRecipe(UserSaveRecipeRequest request);
    
    void unsaveRecipe(Long id);
    
    List<UserSaveRecipe> getSavedRecipesByUserId(Long userId);
}
