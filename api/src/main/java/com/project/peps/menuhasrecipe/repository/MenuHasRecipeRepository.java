package com.project.peps.menuhasrecipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.peps.menuhasrecipe.model.MenuHasRecipe;

public interface MenuHasRecipeRepository extends JpaRepository<MenuHasRecipe, Long> {

    List<MenuHasRecipe> findByMenuId(Long menuId);
    
    boolean existsByMenuIdAndRecipeId(Long menuId, Long recipeId);
}
