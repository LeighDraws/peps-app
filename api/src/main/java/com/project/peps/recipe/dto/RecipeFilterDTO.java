package com.project.peps.recipe.dto;

import com.project.peps.recipe.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeFilterDTO {
    private Long countryId;
    private Category category;
    private List<Long> includedIngredientIds;
    private List<Long> excludedIngredientIds;
}
