package com.project.peps.ingredient.dto;

import com.project.peps.ingredient.model.IngredientCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientResponse {
    private Long id;
    private String name;
    private IngredientCategory category;
}
