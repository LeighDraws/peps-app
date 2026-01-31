package com.project.peps.recipe.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.project.peps.country.mapper.CountryMapper;
import com.project.peps.country.model.Country;
import com.project.peps.recipe.dto.RecipeRequest;
import com.project.peps.recipe.dto.RecipeResponse;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.mapper.UserMapper;
import com.project.peps.user.model.User;

@Component
public class RecipeMapper {

    private final CountryMapper countryMapper;

    private final UserMapper userMapper;

    public RecipeMapper(UserMapper userMapper, CountryMapper countryMapper) {
        this.userMapper = userMapper;
        this.countryMapper = countryMapper;
    }

    public Recipe toRecipe(RecipeRequest recipeRequest) {
        if (recipeRequest == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setName(recipeRequest.getName());
        recipe.setDescription(recipeRequest.getDescription());
        recipe.setImageUrl(recipeRequest.getImageUrl());
        recipe.setPreparationDuration(recipeRequest.getPreparationDuration());
        recipe.setCategory(recipeRequest.getCategory());
        recipe.setPriceRange(recipeRequest.getPriceRange());
        recipe.setDifficulty(recipeRequest.getDifficulty());

        if (recipeRequest.getCountryId() != null) {
            Country country = new Country();
            country.setId(recipeRequest.getCountryId());
            recipe.setCountry(country);
        }

        if (recipeRequest.getUserId() != null) {
            User user = new User();
            user.setId(recipeRequest.getUserId());
            recipe.setUser(user);
        }

        return recipe;
    }

    public RecipeResponse toRecipeResponse(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeResponse recipeResponse = new RecipeResponse();
        recipeResponse.setId(recipe.getId());
        recipeResponse.setName(recipe.getName());
        recipeResponse.setDescription(recipe.getDescription());
        recipeResponse.setImageUrl(recipe.getImageUrl());
        recipeResponse.setPreparationDuration(recipe.getPreparationDuration());
        recipeResponse.setCategory(recipe.getCategory() != null ? recipe.getCategory().name() : null);
        recipeResponse.setPriceRange(recipe.getPriceRange() != null ? recipe.getPriceRange().name() : null);
        recipeResponse.setDifficulty(recipe.getDifficulty() != null ? recipe.getDifficulty().name() : null);
        if (recipe.getCountry() != null) {
            recipeResponse.setCountry(countryMapper.toCountryResponse(recipe.getCountry()));
        }
        if (recipe.getUser() != null) {
            recipeResponse.setUser(userMapper.toResponse(recipe.getUser()));
        }
        recipeResponse.setCreatedAt(recipe.getCreatedAt());
        recipeResponse.setUpdatedAt(recipe.getUpdatedAt());

        return recipeResponse;
    }

    public void updateRecipeFromRequest(RecipeRequest request, Recipe entity) {
        if (request == null || entity == null) {
            return;
        }

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setImageUrl(request.getImageUrl());
        entity.setPreparationDuration(request.getPreparationDuration());
        entity.setCategory(request.getCategory());
        entity.setPriceRange(request.getPriceRange());
        entity.setDifficulty(request.getDifficulty());

        if (request.getCountryId() != null) {
            Country country = new Country();
            country.setId(request.getCountryId());
            entity.setCountry(country);
        }

        if (request.getUserId() != null) {
            User user = new User();
            user.setId(request.getUserId());
            entity.setUser(user);
        }
    }

    public List<RecipeResponse> toRecipeResponseList(List<Recipe> recipes) {
        if (recipes == null) {
            return null;
        }
        return recipes.stream()
                .map(this::toRecipeResponse)
                .collect(Collectors.toList());
    }
}
