package com.project.peps.shared.health;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

  private final JdbcTemplate jdbcTemplate;

  public HealthService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // Service méthode qui renvoie le statut de santé de l'API (response entity pour renvoyer le bon
  // code HTTP + message)
  public ResponseEntity<Map<String, String>> checkHealth() {
    try {
      Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
      if (one != null && one == 1) {
        return ResponseEntity.ok(
            Map.of("status", "ok", "message", "Pomme d'API, tapis tapis gris"));
      } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("status", "error", "message", "C'est cassé et on sait pas pourquoi"));
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(
              Map.of(
                  "status",
                  "error",
                  "message",
                  "La base de données te demande d'aller voir ailleurs si elle y est"));
    }
  }
}
