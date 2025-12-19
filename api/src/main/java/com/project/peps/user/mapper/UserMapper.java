package com.project.peps.user.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.model.User;

@Component
public class UserMapper {

    public User toEntity(UserRequest userRequest) {
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

    public UserResponse toResponse(User user) {
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

    // Méthode qui met à jour une entité existante sans toucher à l'ID
    public void updateUserFromRequest(UserRequest request, User entity) {
        if (request == null || entity == null) {
            return;
        }
        
        entity.setPseudo(request.getPseudo());
        entity.setEmail(request.getEmail());
        entity.setAvatarUrl(request.getAvatarUrl());
        
        // Gestion du mot de passe (si présent dans la request)
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            entity.setPassword(request.getPassword()); 
            // TODO : Gérer l'encodage du mot de passe dans un service approprié
        }
    }

    public List<UserResponse> toResponseList(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
