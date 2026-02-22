package com.project.peps.auth.service;

import com.project.peps.auth.dto.LoginRequest;
import com.project.peps.auth.dto.RegisterRequest;
import com.project.peps.shared.config.JwtService;
import com.project.peps.user.dto.UserResponse;
import com.project.peps.user.mapper.UserMapper;
import com.project.peps.user.model.AuthProvider;
import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public ResponseEntity<UserResponse> register(RegisterRequest request) {
        User user = User.builder()
                .pseudo(request.getPseudo())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authProvider(AuthProvider.LOCAL)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        return createAuthenticatedResponse(user);
    }

    public ResponseEntity<UserResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        return createAuthenticatedResponse(user);
    }

    public ResponseEntity<Void> logout() {
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        ResponseCookie refreshCookie = ResponseCookie.from("jwt_refresh", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return ResponseEntity.ok().headers(headers).build();
    }

    public ResponseEntity<Void> refreshToken(HttpServletRequest request) {
        Optional<String> refreshTokenOpt = Arrays.stream(request.getCookies())
                .filter(c -> "jwt_refresh".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst();

        if (refreshTokenOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String refreshToken = refreshTokenOpt.get();
        String userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                ResponseCookie jwtCookie = createJwtCookie(user);
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
                return ResponseEntity.ok().headers(headers).build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    private ResponseEntity<UserResponse> createAuthenticatedResponse(User user) {
        ResponseCookie jwtCookie = createJwtCookie(user);
        ResponseCookie refreshCookie = createRefreshCookie(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        UserResponse userResponse = userMapper.toResponse(user);

        return ResponseEntity.ok().headers(headers).body(userResponse);
    }

    private ResponseCookie createJwtCookie(UserDetails user) {
        String jwt = jwtService.generateToken(user);
        return ResponseCookie.from("jwt", jwt)
                .httpOnly(true)
                .secure(true) 
                .path("/")
                .maxAge(jwtService.getJwtExpiration() / 1000)
                .build();
    }

    private ResponseCookie createRefreshCookie(UserDetails user) {
        String refreshToken = jwtService.generateRefreshToken(user);
        return ResponseCookie.from("jwt_refresh", refreshToken)
                .httpOnly(true)
                .secure(true) 
                .path("/")
                .maxAge(jwtService.getRefreshExpiration() / 1000)
                .build();
    }
}
