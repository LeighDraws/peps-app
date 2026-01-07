package com.project.peps.usersaverecipe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.service.RecipeService;
import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;
import com.project.peps.usersaverecipe.dto.UserSaveRecipeRequest;
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
    public UserSaveRecipe saveRecipe(UserSaveRecipeRequest request) {
        if (userSaveRecipeRepository.existsByUserIdAndRecipeId(request.getUserId(), request.getRecipeId())) {
            throw new IllegalArgumentException("Recipe is already saved by this user");
        }

        Recipe recipe = recipeService.findRecipeById(request.getRecipeId());
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        UserSaveRecipe userSaveRecipe = UserSaveRecipe.builder()
                .user(user)
                .recipe(recipe)
                .build();

        return userSaveRecipeRepository.save(userSaveRecipe);
    }

    @Override
    public void unsaveRecipe(Long id) {
        if (!userSaveRecipeRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserSaveRecipe", "id", id);
        }
        userSaveRecipeRepository.deleteById(id);
    }

    @Override
    public List<UserSaveRecipe> getSavedRecipesByUserId(Long userId) {
        return userSaveRecipeRepository.findByUserId(userId);
    }
}
