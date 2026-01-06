package com.project.peps.menu.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
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
public class MenuRequest {

    @NotBlank(message = "Le nom du menu ne peut pas être vide.")
    private String name;

    private LocalDateTime date;

    @NotNull(message = "L'utilisateur est obligatoire.")
    private Long userId;
}
