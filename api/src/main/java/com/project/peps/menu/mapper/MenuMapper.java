package com.project.peps.menu.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.peps.menu.dto.MenuRequest;
import com.project.peps.menu.dto.MenuResponse;
import com.project.peps.menu.model.Menu;

@Component
public class MenuMapper {

    public Menu toEntity(MenuRequest request) {
        if (request == null) {
            return null;
        }
        return Menu.builder()
                .name(request.getName())
                .date(request.getDate())
                .build();
    }

    public MenuResponse toResponse(Menu menu) {
        if (menu == null) {
            return null;
        }
        return MenuResponse.builder()
                .id(menu.getId())
                .name(menu.getName())
                .date(menu.getDate())
                .userId(menu.getUser() != null ? menu.getUser().getId() : null)
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .build();
    }

    public void updateEntityFromRequest(MenuRequest request, Menu entity) {
        if (request == null || entity == null) {
            return;
        }
        entity.setName(request.getName());
        entity.setDate(request.getDate());
    }

    public List<MenuResponse> toResponseList(List<Menu> menus) {
        if (menus == null) {
            return null;
        }
        return menus.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
