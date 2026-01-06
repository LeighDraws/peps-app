package com.project.peps.menuhasrecipe.dto;

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
public class MenuHasRecipeRequest {

    @NotNull(message = "L'ID du menu est obligatoire.")
    private Long menuId;

    @NotNull(message = "L'ID de la recette est obligatoire.")
    private Long recipeId;
}
