package com.project.peps.ingredient.dto;

import com.project.peps.ingredient.model.IngredientCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {
    @NotBlank(message = "Ingredient name cannot be blank")
    private String name;

    private IngredientCategory category;
}
