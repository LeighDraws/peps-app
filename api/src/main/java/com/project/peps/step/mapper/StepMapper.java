package com.project.peps.step.mapper;

import com.project.peps.recipe.model.Recipe;
import com.project.peps.step.dto.StepRequest;
import com.project.peps.step.dto.StepResponse;
import com.project.peps.step.model.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StepMapper {

    @Mapping(source = "stepRequest.imageUrl", target = "imageUrl")
    @Mapping(source = "recipe", target = "recipe")
    @Mapping(target = "id", ignore = true)
    Step toEntity(StepRequest stepRequest, Recipe recipe);

    @Mapping(source = "recipe.id", target = "recipeId")
    StepResponse toDto(Step step);

    @Mapping(source = "stepRequest.imageUrl", target = "imageUrl")
    @Mapping(source = "recipe", target = "recipe")
    @Mapping(target = "id", ignore = true)
    void updateStepFromDto(StepRequest stepRequest, @MappingTarget Step step, Recipe recipe);
}