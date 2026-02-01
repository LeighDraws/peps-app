package com.project.peps.recipehasingredient.mapper;

import com.project.peps.recipehasingredient.dto.RecipeHasIngredientResponse;
import com.project.peps.recipehasingredient.model.RecipeHasIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeHasIngredientMapper {

    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "ingredient.id", target = "ingredientId")
    @Mapping(source = "ingredient.name", target = "ingredientName")
    RecipeHasIngredientResponse toResponse(RecipeHasIngredient entity);

    List<RecipeHasIngredientResponse> toResponseList(List<RecipeHasIngredient> list);
}