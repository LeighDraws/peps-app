package com.project.peps.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.peps.menu.repository.MenuRepository;
import com.project.peps.recipe.model.Recipe;
import com.project.peps.recipe.repository.RecipeRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;
import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final RecipeRepository recipeRepository;

    public UserServiceImpl(UserRepository userRepository, MenuRepository menuRepository,
            RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        // Avant de supprimer on anonymise les recettes de l'utilisateur pour éviter les
        // problèmes de contraintes d'intégrité
        List<Recipe> userRecipes = recipeRepository.findByUserId(id);
        for (Recipe recipe : userRecipes) {
            recipe.setUser(null);
        };
        recipeRepository.saveAll(userRecipes);

        // Manual cascade delete for menus
        menuRepository.deleteByUserId(id);

        userRepository.deleteById(id);
        return user;
    }
}
