package com.project.peps.usersaverecipe.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.peps.usersaverecipe.dto.UserSaveRecipeResponse;
import com.project.peps.usersaverecipe.model.UserSaveRecipe;

@Component
public class UserSaveRecipeMapper {

    public UserSaveRecipeResponse toResponse(UserSaveRecipe entity) {
        if (entity == null) {
            return null;
        }
        return UserSaveRecipeResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .recipeId(entity.getRecipe() != null ? entity.getRecipe().getId() : null)
                .recipeName(entity.getRecipe() != null ? entity.getRecipe().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public List<UserSaveRecipeResponse> toResponseList(List<UserSaveRecipe> list) {
        if (list == null) {
            return null;
        }
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
