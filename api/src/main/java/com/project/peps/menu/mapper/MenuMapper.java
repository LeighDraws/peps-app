package com.project.peps.menu.mapper;

import com.project.peps.menu.dto.MenuRequest;
import com.project.peps.menu.dto.MenuResponse;
import com.project.peps.menu.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    Menu toEntity(MenuRequest request);

    @Mapping(source = "user.id", target = "userId")
    MenuResponse toResponse(Menu menu);

    void updateEntityFromRequest(MenuRequest request, @MappingTarget Menu entity);

    List<MenuResponse> toResponseList(List<Menu> menus);
}