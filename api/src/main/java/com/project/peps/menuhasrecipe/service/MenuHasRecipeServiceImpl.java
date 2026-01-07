package com.project.peps.menuhasrecipe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.menu.model.Menu;
import com.project.peps.menu.service.MenuService;
import com.project.peps.menuhasrecipe.model.MenuHasRecipe;
import com.project.peps.menuhasrecipe.repository.MenuHasRecipeRepository;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.service.RecipeService;
import com.project.peps.shared.exception.ResourceNotFoundException;

@Service
public class MenuHasRecipeServiceImpl implements MenuHasRecipeService {

    private final MenuHasRecipeRepository menuHasRecipeRepository;
    private final MenuService menuService;
    private final RecipeService recipeService;

    public MenuHasRecipeServiceImpl(MenuHasRecipeRepository menuHasRecipeRepository, MenuService menuService, RecipeService recipeService) {
        this.menuHasRecipeRepository = menuHasRecipeRepository;
        this.menuService = menuService;
        this.recipeService = recipeService;
    }

    @Override
    public MenuHasRecipe addRecipeToMenu(Long menuId, Long recipeId) {
        // Verification si la recette est déjà dans le menu
        if (menuHasRecipeRepository.existsByMenuIdAndRecipeId(menuId, recipeId)) {
            throw new IllegalArgumentException("Recipe is already in the menu");
        }

        // Récupération du menu et de la recette par leur ID
        Menu menu = menuService.findMenuById(menuId);
        Recipe recipe = recipeService.findRecipeById(recipeId);

        // Recreation de l'association MenuHasRecipe (ne peut pas être fait dans le mapper)
        MenuHasRecipe menuHasRecipe = MenuHasRecipe.builder()
                .menu(menu)
                .recipe(recipe)
                .build();

        // Enrigistrement de l'association dans la base de données
        return menuHasRecipeRepository.save(menuHasRecipe);
    }

    @Override
    public void removeRecipeFromMenu(Long menuHasRecipeId) {
        if (!menuHasRecipeRepository.existsById(menuHasRecipeId)) {
             throw new ResourceNotFoundException("MenuHasRecipe", "id", menuHasRecipeId);
        }
        menuHasRecipeRepository.deleteById(menuHasRecipeId);
    }

    @Override
    public List<MenuHasRecipe> findRecipesByMenuId(Long menuId) {
        return menuHasRecipeRepository.findByMenuId(menuId);
    }
}
