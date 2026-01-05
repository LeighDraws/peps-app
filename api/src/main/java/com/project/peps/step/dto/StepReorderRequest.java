package com.project.peps.step.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StepReorderRequest {
    @NotNull
    private Long id;

    @NotNull
    private Integer stepNumber;
}
