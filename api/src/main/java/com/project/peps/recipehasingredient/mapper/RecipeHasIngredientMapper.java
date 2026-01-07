package com.project.peps.recipehasingredient.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.peps.recipehasingredient.dto.RecipeHasIngredientResponse;
import com.project.peps.recipehasingredient.model.RecipeHasIngredient;

@Component
public class RecipeHasIngredientMapper {

    // Pas de méthode toEntity car cette entité est créée via les services RecipeService et IngredientService
    // Le mapper n'a pas accès à la logique métier nécessaire pour créer cette entité

    public RecipeHasIngredientResponse toResponse(RecipeHasIngredient entity) {
        if (entity == null) {
            return null;
        }
        return RecipeHasIngredientResponse.builder()
                .id(entity.getId())
                .recipeId(entity.getRecipe() != null ? entity.getRecipe().getId() : null)
                .ingredientId(entity.getIngredient() != null ? entity.getIngredient().getId() : null)
                .ingredientName(entity.getIngredient() != null ? entity.getIngredient().getName() : null)
                .quantity(entity.getQuantity())
                .measurementUnit(entity.getMeasurementUnit())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public List<RecipeHasIngredientResponse> toResponseList(List<RecipeHasIngredient> list) {
        if (list == null) {
            return null;
        }
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
