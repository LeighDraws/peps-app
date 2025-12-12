package com.project.peps.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.peps.user.model.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

    // SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);

    // Verifie si un utilisateur existe avec le pseudo donné
    boolean existsByPseudo(String pseudo);

    // Verifie si un utilisateur existe avec l'email donné
    boolean existsByEmail(String email);
}
