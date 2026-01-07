package com.project.peps.recipehasingredient.dto;

import java.time.LocalDateTime;

import com.project.peps.recipehasingredient.model.MeasurementUnit;

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
public class RecipeHasIngredientResponse {

    private Long id;
    private Long recipeId;
    private Long ingredientId;
    private String ingredientName;
    private Double quantity;
    private MeasurementUnit measurementUnit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
