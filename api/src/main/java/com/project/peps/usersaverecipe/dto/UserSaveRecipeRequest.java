package com.project.peps.usersaverecipe.dto;

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
public class UserSaveRecipeRequest {

    @NotNull(message = "L'ID de l'utilisateur est obligatoire.")
    private Long userId;

    @NotNull(message = "L'ID de la recette est obligatoire.")
    private Long recipeId;
}
