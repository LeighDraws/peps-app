package com.project.peps.user.mapper;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    void updateUserFromRequest(UserRequest request, @MappingTarget User entity);

    List<UserResponse> toResponseList(List<User> users);
}