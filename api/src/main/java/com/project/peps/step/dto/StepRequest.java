package com.project.peps.step.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StepRequest {
    @NotNull(message = "Step number cannot be null")
    @Positive(message = "Step number must be positive")
    private Integer stepNumber;

    @NotBlank(message = "Instruction cannot be blank")
    private String instruction;

    private String imageUrl;

    @NotNull(message = "Recipe ID cannot be null")
    private Long recipeId;
}
