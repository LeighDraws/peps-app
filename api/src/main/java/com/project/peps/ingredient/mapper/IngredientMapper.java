package com.project.peps.ingredient.mapper;

import com.project.peps.ingredient.dto.IngredientRequest;
import com.project.peps.ingredient.dto.IngredientResponse;
import com.project.peps.ingredient.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientResponse toIngredientResponse(Ingredient ingredient);

    Ingredient toIngredient(IngredientRequest ingredientRequest);

    void updateIngredientFromRequest(IngredientRequest request, @MappingTarget Ingredient ingredient);
}