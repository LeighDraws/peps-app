package com.project.peps.menuhasrecipe.mapper;

import com.project.peps.menuhasrecipe.dto.MenuHasRecipeResponse;
import com.project.peps.menuhasrecipe.model.MenuHasRecipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuHasRecipeMapper {

    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "recipe.name", target = "recipeName")
    @Mapping(source = "recipe.imageUrl", target = "recipeImageUrl")
    MenuHasRecipeResponse toResponse(MenuHasRecipe menuHasRecipe);

    List<MenuHasRecipeResponse> toResponseList(List<MenuHasRecipe> list);
}