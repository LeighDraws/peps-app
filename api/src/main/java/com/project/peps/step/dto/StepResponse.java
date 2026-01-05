package com.project.peps.step.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StepResponse {
    Long id;
    Integer stepNumber;
    String instruction;
    String imageUrl;
    Long recipeId;
}
