package com.project.peps.user.service;

import java.util.List;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.model.User;

public interface UserService {
    
    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByEmail(String email);

    User createUser(User user);

    User updateUser(Long id, UserRequest userRequest);

    User deleteById(Long id) ;
}
