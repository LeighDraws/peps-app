package com.project.peps.tag.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagRequest {

    @NotBlank(message = "Le nom du tag ne peut pas être vide")
    private String name;
}