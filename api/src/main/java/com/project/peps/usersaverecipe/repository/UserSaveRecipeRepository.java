package com.project.peps.usersaverecipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.peps.usersaverecipe.model.UserSaveRecipe;

@Repository
public interface UserSaveRecipeRepository extends JpaRepository<UserSaveRecipe, Long> {
    
    List<UserSaveRecipe> findByUserId(Long userId);
    
    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
}
