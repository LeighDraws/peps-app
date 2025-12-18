package com.project.peps.ingredient.mapper;

import com.project.peps.ingredient.dto.IngredientRequest;
import com.project.peps.ingredient.dto.IngredientResponse;
import com.project.peps.ingredient.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientResponse toIngredientResponse(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .category(ingredient.getCategory())
                .build();
    }

    public Ingredient toIngredient(IngredientRequest ingredientRequest) {
        if (ingredientRequest == null) {
            return null;
        }
        return Ingredient.builder()
                .name(ingredientRequest.getName())
                .category(ingredientRequest.getCategory())
                .build();
    }

    public void updateIngredientFromRequest(IngredientRequest request, Ingredient ingredient) {
        if (request == null || ingredient == null) {
            return;
        }
        ingredient.setName(request.getName());
        ingredient.setCategory(request.getCategory());
    }
}
