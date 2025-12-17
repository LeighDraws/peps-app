package com.project.peps.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.user.dto.UserRequest;
import com.project.peps.user.exception.UserNotFoundException;
import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserRequest userRequest) {
        User existingUser = findById(id);
        existingUser.setPseudo(userRequest.getPseudo());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword()); // TODO: Add password encoding
        existingUser.setAvatarUrl(userRequest.getAvatarUrl());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(Long id) {
        // TODO: check if user exists before deleting
        userRepository.deleteById(id);
    }
}
