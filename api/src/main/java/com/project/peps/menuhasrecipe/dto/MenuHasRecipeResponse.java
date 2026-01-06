package com.project.peps.menuhasrecipe.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuHasRecipeResponse {

    private Long id;
    private Long menuId;
    private Long recipeId;
    private String recipeName;
    private String recipeImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
