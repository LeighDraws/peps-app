package com.project.peps.recipe.dto;

import com.project.peps.recipe.model.Category;
import com.project.peps.recipe.model.Difficulty;
import com.project.peps.recipe.model.PriceRange;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest {

    @NotBlank(message = "The name cannot be null.")
    private String name;

    private String description;

    private String imageUrl;

    private Integer preparationDuration;

    private Category category;

    private PriceRange priceRange;

    private Difficulty difficulty;

    @NotBlank(message = "A country ID must be provided.")
    private Long countryId;

    @NotBlank(message = "A user ID must be provided.")
    private Long userId;
}
