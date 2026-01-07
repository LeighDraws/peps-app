package com.project.peps.recipehasingredient.dto;

import com.project.peps.recipehasingredient.model.MeasurementUnit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class RecipeHasIngredientRequest {

    @NotNull(message = "L'ID de la recette est obligatoire.")
    private Long recipeId;

    @NotNull(message = "L'ID de l'ingrédient est obligatoire.")
    private Long ingredientId;

    @NotNull(message = "La quantité est obligatoire.")
    @Positive(message = "La quantité doit être supérieure à 0.")
    private Double quantity;

    @NotNull(message = "L'unité de mesure est obligatoire.")
    private MeasurementUnit measurementUnit;
}
