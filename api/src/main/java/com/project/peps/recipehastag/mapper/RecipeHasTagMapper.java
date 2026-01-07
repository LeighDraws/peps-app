package com.project.peps.recipehastag.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.peps.recipehastag.dto.RecipeHasTagResponse;
import com.project.peps.recipehastag.model.RecipeHasTag;

@Component
public class RecipeHasTagMapper {

    public RecipeHasTagResponse toResponse(RecipeHasTag entity) {
        if (entity == null) {
            return null;
        }
        return RecipeHasTagResponse.builder()
                .id(entity.getId())
                .recipeId(entity.getRecipe() != null ? entity.getRecipe().getId() : null)
                .tagId(entity.getTag() != null ? entity.getTag().getId() : null)
                .tagName(entity.getTag() != null ? entity.getTag().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public List<RecipeHasTagResponse> toResponseList(List<RecipeHasTag> list) {
        if (list == null) {
            return null;
        }
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
