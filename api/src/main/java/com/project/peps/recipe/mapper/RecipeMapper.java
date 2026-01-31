package com.project.peps.recipe.mapper;

import com.project.peps.country.mapper.CountryMapper;
import com.project.peps.recipe.dto.RecipeRequest;
import com.project.peps.recipe.dto.RecipeResponse;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CountryMapper.class})
public interface RecipeMapper {

    @Mapping(source = "countryId", target = "country.id")
    @Mapping(source = "userId", target = "user.id")
    Recipe toRecipe(RecipeRequest recipeRequest);

    @Mapping(source = "country", target = "country")
    @Mapping(source = "user", target = "user")
    RecipeResponse toRecipeResponse(Recipe recipe);

    @Mapping(source = "countryId", target = "country.id")
    @Mapping(source = "userId", target = "user.id")
    void updateRecipeFromRequest(RecipeRequest request, @MappingTarget Recipe entity);

    List<RecipeResponse> toRecipeResponseList(List<Recipe> recipes);
}