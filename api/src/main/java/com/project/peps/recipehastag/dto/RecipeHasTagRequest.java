package com.project.peps.recipehastag.dto;

import jakarta.validation.constraints.NotNull;
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
public class RecipeHasTagRequest {

    @NotNull(message = "L'ID de la recette est obligatoire.")
    private Long recipeId;

    @NotNull(message = "L'ID du tag est obligatoire.")
    private Long tagId;
}
