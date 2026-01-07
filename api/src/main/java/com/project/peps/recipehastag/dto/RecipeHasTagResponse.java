package com.project.peps.recipehastag.dto;

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
public class RecipeHasTagResponse {

    private Long id;
    private Long recipeId;
    private Long tagId;
    private String tagName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
