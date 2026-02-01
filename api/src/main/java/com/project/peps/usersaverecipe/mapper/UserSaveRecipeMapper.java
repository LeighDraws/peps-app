package com.project.peps.usersaverecipe.mapper;

import com.project.peps.usersaverecipe.dto.UserSaveRecipeResponse;
import com.project.peps.usersaverecipe.model.UserSaveRecipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSaveRecipeMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "recipe.name", target = "recipeName")
    UserSaveRecipeResponse toResponse(UserSaveRecipe entity);

    List<UserSaveRecipeResponse> toResponseList(List<UserSaveRecipe> list);
}
