package com.project.peps.shared.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service pour la gestion des JSON Web Tokens (JWT).
 * Fournit des méthodes pour la génération, l'extraction d'informations
 * et la validation des tokens JWT.
 */
@Service
public class JwtService {

    // Clé secrète pour la signature des tokens JWT, injectée depuis les propriétés de l'application
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    // Durée du token
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    // Durée du token de rafraîchissement
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    // Extrait le nom (username mais pas pseudo, on utilisera l'email) depuis le token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait une revendication spécifique (claim) du token JWT.
     *
     * token - Le token JWT.
     * claimsResolver - Fonction pour résoudre la revendication souhaitée.
     * <T> - Type de la revendication à extraire.
     * return - La revendication extraite.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Génère un token JWT sans revendications supplémentaires.
     *
     * userDetails - Les détails de l'utilisateur pour lequel le token est généré. 
     * Type UserDetails vient de Spring Security et contient des informations sur l'utilisateur, comme le nom d'utilisateur, les rôles, etc.
     * return - Le token JWT généré.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Génère un token JWT avec des revendications supplémentaires.
     *
     * extraClaims - Revendications supplémentaires à inclure dans le token.
     * userDetails - Les détails de l'utilisateur pour lequel le token est généré.
     * return - Le token JWT généré.
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Génère un token de rafraîchissement JWT.
     *
     * userDetails - Les détails de l'utilisateur pour lequel le token est généré.
     * return - Le token de rafraîchissement JWT généré.
     */
    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    /**
     * Construit un token JWT avec les revendications, l'utilisateur et l'expiration spécifiés.
     * return - Le token JWT construit.
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valide un token JWT par rapport aux détails de l'utilisateur.
     * Vérifie que le nom d'utilisateur correspond et que le token n'est pas expiré.
     *
     * return - Vrai si le token est valide, faux sinon.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Vérifie si le token JWT est expiré.
     *
     * return - Vrai si le token est expiré, faux sinon.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrait la date d'expiration du token JWT.
     *
     * return - La date d'expiration.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrait toutes les revendications (claims) du token JWT.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Récupère la clé de signature à partir de la clé secrète.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}