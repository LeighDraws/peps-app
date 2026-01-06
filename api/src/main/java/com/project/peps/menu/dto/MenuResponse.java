package com.project.peps.menu.dto;

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
public class MenuResponse {

    private Long id;
    private String name;
    private LocalDateTime date;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
