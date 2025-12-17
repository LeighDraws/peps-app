package com.project.peps.user.service;

import com.project.peps.user.model.User;
import java.util.Optional;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    User save(User user);

    void deleteById(Long id);
}
