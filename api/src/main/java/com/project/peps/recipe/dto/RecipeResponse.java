package com.project.peps.recipe.dto;

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
public class RecipeResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer preparationDuration;
    private String category;
    private String priceRange;
    private String difficulty;
    private Long countryId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}