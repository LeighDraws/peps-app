package com.project.peps.usersaverecipe.dto;

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
public class UserSaveRecipeResponse {

    private Long id;
    private Long userId;
    private Long recipeId;
    private String recipeName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
