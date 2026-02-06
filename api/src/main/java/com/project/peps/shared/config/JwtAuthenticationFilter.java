package com.project.peps.shared.config;

import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Injection du service JWT pour la gestion des tokens
    private final JwtService jwtService;
    // Injection du repository utilisateur pour récupérer les détails de l'utilisateur
    private final UserRepository userRepository;

    // Cette méthode est appelée pour chaque requête HTTP
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, // La requête HTTP entrante
            HttpServletResponse response, // La réponse HTTP sortante
            FilterChain filterChain // Permet de passer la requête au prochain filtre de la chaîne
    ) throws ServletException, IOException {
        // Récupère l'en-tête "Authorization" de la requête
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Vérifie si l'en-tête "Authorization" est manquant ou ne commence pas par "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Si c'est le cas, passe au filtre suivant sans authentification
            filterChain.doFilter(request, response);
            return;
        }

        // Extrait le token JWT (la partie après "Bearer ")
        jwt = authHeader.substring(7);
        // Extrait l'e-mail de l'utilisateur à partir du token JWT
        userEmail = jwtService.extractUsername(jwt);

        // Si l'e-mail de l'utilisateur est présent et qu'aucune authentification n'est déjà définie dans le contexte de sécurité
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Charge l'utilisateur depuis la base de données via son e-mail
            User user = userRepository.findByEmail(userEmail)
                    .orElse(null); // Si l'utilisateur n'est pas trouvé, retourne null

            // Si l'utilisateur existe et que le token est valide pour cet utilisateur
            if (user != null && jwtService.isTokenValid(jwt, user)) {
                // Crée un objet d'authentification Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, // L'objet utilisateur (principal)
                        null, // Les informations d'identification (credentials), ici null car déjà validées par le JWT
                        user.getAuthorities() // Les rôles/autorisations de l'utilisateur
                );
                // Définit les détails de l'authentification (comme l'adresse IP de la requête)
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Met à jour le contexte de sécurité avec le nouvel objet d'authentification
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Passe la requête et la réponse au prochain filtre de la chaîne
        filterChain.doFilter(request, response);
    }
}
