package com.project.peps.auth.service;

import com.project.peps.auth.dto.AuthResponse;
import com.project.peps.auth.dto.LoginRequest;
import com.project.peps.auth.dto.RegisterRequest;
import com.project.peps.shared.config.JwtService;
import com.project.peps.user.mapper.UserMapper;
import com.project.peps.user.model.AuthProvider;
import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    // Méthode pour l'inscription d'un utilisateur, prend un RegisterRequest et retourne un AuthResponse avec le token JWT
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .pseudo(request.getPseudo())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authProvider(AuthProvider.LOCAL) // Toujours LOCAL pour les inscriptions classiques
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        
        String jwtToken = jwtService.generateToken(user);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .user(userMapper.toResponse(user))
                .build();
    }

    // Méthode pour la connexion d'un utilisateur, prend un LoginRequest et retourne un AuthResponse avec le token JWT
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        
        String jwtToken = jwtService.generateToken(user);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .user(userMapper.toResponse(user))
                .build();
    }
}
