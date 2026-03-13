package com.project.peps.ingredient.mapper;

import com.project.peps.ingredient.dto.IngredientRequest;
import com.project.peps.ingredient.dto.IngredientResponse;
import com.project.peps.ingredient.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngredientMapper {

    IngredientResponse toIngredientResponse(Ingredient ingredient);

    Ingredient toIngredient(IngredientRequest ingredientRequest);

    void updateEntityFromRequest(IngredientRequest request, @MappingTarget Ingredient ingredient);
}