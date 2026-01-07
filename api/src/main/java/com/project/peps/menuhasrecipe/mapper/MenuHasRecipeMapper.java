package com.project.peps.menuhasrecipe.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.peps.menuhasrecipe.dto.MenuHasRecipeResponse;
import com.project.peps.menuhasrecipe.model.MenuHasRecipe;

@Component
public class MenuHasRecipeMapper {

    // Pas de méthode toEntity car cette entité est créée via les services MenuService et RecipeService
    // Le mapper n'a pas accès à la logique métier nécessaire pour créer cette entité

    public MenuHasRecipeResponse toResponse(MenuHasRecipe menuHasRecipe) {
        if (menuHasRecipe == null) {
            return null;
        }
        return MenuHasRecipeResponse.builder()
                .id(menuHasRecipe.getId())
                .menuId(menuHasRecipe.getMenu() != null ? menuHasRecipe.getMenu().getId() : null)
                .recipeId(menuHasRecipe.getRecipe() != null ? menuHasRecipe.getRecipe().getId() : null)
                .recipeName(menuHasRecipe.getRecipe() != null ? menuHasRecipe.getRecipe().getName() : null)
                .recipeImageUrl(menuHasRecipe.getRecipe() != null ? menuHasRecipe.getRecipe().getImageUrl() : null)
                .createdAt(menuHasRecipe.getCreatedAt())
                .updatedAt(menuHasRecipe.getUpdatedAt())
                .build();
    }

    public List<MenuHasRecipeResponse> toResponseList(List<MenuHasRecipe> list) {
        if (list == null) {
            return null;
        }
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
