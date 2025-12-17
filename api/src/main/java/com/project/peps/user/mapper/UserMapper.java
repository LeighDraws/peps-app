package com.project.peps.user.mapper;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.model.User;
import java.util.List;
import java.util.stream.Collectors;

public final class UserMapper {

    private UserMapper() {
        // Création du constructeur en private pour éviter l'instanciation directe de la classe (pas de new UserMapper())
        // UserMapper est une classe utilitaire on ne peut utiliser que toEntity et toResponse
    }

    public static User toEntity(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        User user = new User();
        user.setPseudo(userRequest.getPseudo());
        user.setEmail(userRequest.getEmail());
        // TODO : Encoder le mot de passe avant de l'enregistrer dans la base de données
        user.setPassword(userRequest.getPassword());
        user.setAvatarUrl(userRequest.getAvatarUrl());
        return user;
    }

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setPseudo(user.getPseudo());
        userResponse.setEmail(user.getEmail());
        userResponse.setAvatarUrl(user.getAvatarUrl());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }

    public static List<UserResponse> toResponseList(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }
}
