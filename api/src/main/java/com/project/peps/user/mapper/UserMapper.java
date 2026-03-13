package com.project.peps.user.mapper;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    @Mapping(target = "password", ignore = true)
    void updateEntityFromRequest(UserRequest request, @MappingTarget User entity);

    @AfterMapping
    default void updatePassword(UserRequest request, @MappingTarget User entity) {
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            entity.setPassword(request.getPassword());
        }
    }

    List<UserResponse> toResponseList(List<User> users);
}