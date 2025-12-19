package com.project.peps.user.service;

import java.util.List;

import com.project.peps.user.model.User;

public interface UserService {
    
    // Pas d'utilisation de DTO ici, le service travaille avec les entités 100% concentré sur la logique métier

    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByEmail(String email);

    User save(User user); // Cette méthode peut être utilisée pour create et update

    User deleteById(Long id) ;
}
