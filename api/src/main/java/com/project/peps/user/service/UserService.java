package com.project.peps.user.service;

import java.util.List;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.model.User;

public interface UserService {
    
    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    User save(User user);

    User updateUser(Long id, UserRequest userRequest);

    void deleteById(Long id);
}
