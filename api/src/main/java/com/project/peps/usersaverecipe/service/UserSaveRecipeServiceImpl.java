package com.project.peps.usersaverecipe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.service.RecipeService;
import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;
import com.project.peps.usersaverecipe.model.UserSaveRecipe;
import com.project.peps.usersaverecipe.repository.UserSaveRecipeRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;

@Service
public class UserSaveRecipeServiceImpl implements UserSaveRecipeService {

    private final UserSaveRecipeRepository userSaveRecipeRepository;
    private final RecipeService recipeService;
    private final UserRepository userRepository;

    public UserSaveRecipeServiceImpl(UserSaveRecipeRepository userSaveRecipeRepository,
                                     RecipeService recipeService,
                                     UserRepository userRepository) {
        this.userSaveRecipeRepository = userSaveRecipeRepository;
        this.recipeService = recipeService;
        this.userRepository = userRepository;
    }

    @Override
    public UserSaveRecipe saveRecipe(Long recipeId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        if (userSaveRecipeRepository.existsByUserIdAndRecipeId(user.getId(), recipeId)) {
            throw new IllegalArgumentException("Recipe is already saved by this user");
        }

        Recipe recipe = recipeService.findRecipeById(recipeId);

        UserSaveRecipe userSaveRecipe = UserSaveRecipe.builder()
                .user(user)
                .recipe(recipe)
                .build();

        return userSaveRecipeRepository.save(userSaveRecipe);
    }

    @Override
    public void unsaveRecipe(Long recipeId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        if (!userSaveRecipeRepository.existsByUserIdAndRecipeId(user.getId(), recipeId)) {
            throw new ResourceNotFoundException("UserSaveRecipe", "recipeId", recipeId);
        }
        userSaveRecipeRepository.deleteByUserIdAndRecipeId(user.getId(), recipeId);
    }

    @Override
    public List<UserSaveRecipe> getSavedRecipesByUserId(Long userId) {
        return userSaveRecipeRepository.findByUserId(userId);
    }
}
