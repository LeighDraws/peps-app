package com.project.peps.menuhasrecipe.service;

import java.util.List;

import com.project.peps.menuhasrecipe.model.MenuHasRecipe;

public interface MenuHasRecipeService {

    MenuHasRecipe addRecipeToMenu(Long menuId, Long recipeId);

    void removeRecipeFromMenu(Long menuHasRecipeId);

    List<MenuHasRecipe> findRecipesByMenuId(Long menuId);
}
