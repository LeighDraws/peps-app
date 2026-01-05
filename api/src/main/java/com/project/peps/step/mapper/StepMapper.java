package com.project.peps.step.mapper;

import com.project.peps.recipe.model.Recipe;
import com.project.peps.step.dto.StepRequest;
import com.project.peps.step.dto.StepResponse;
import com.project.peps.step.model.Step;
import org.springframework.stereotype.Component;

@Component
public class StepMapper {

    public Step toEntity(StepRequest stepRequest, Recipe recipe) {
        return Step.builder()
                .stepNumber(stepRequest.getStepNumber())
                .instruction(stepRequest.getInstruction())
                .imageUrl(stepRequest.getImageUrl())
                .recipe(recipe)
                .build();
    }

    public StepResponse toDto(Step step) {
        return StepResponse.builder()
                .id(step.getId())
                .stepNumber(step.getStepNumber())
                .instruction(step.getInstruction())
                .imageUrl(step.getImageUrl())
                .recipeId(step.getRecipe().getId())
                .build();
    }

    public void updateStepFromDto(StepRequest stepRequest, Step step, Recipe recipe) {
        step.setStepNumber(stepRequest.getStepNumber());
        step.setInstruction(stepRequest.getInstruction());
        step.setImageUrl(stepRequest.getImageUrl());
        step.setRecipe(recipe);
    }
}
