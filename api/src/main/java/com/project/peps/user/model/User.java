package com.project.peps.user.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String pseudo;

  @Column(nullable = false, unique = true, length = 100) // Contraintes de la base de données
  private String email;

  // Nullable car inutile si connexion via OAuth (Google/Microsoft)
  @Column(name = "password", nullable = true)
  private String password;

  // URL de l'image
  @Column(name = "avatar_url")
  private String avatarUrl;

  // Pour savoir si c'est un compte LOCAL, GOOGLE, MICROSOFT
  @Enumerated(EnumType.STRING)
  @Column(name = "auth_provider")
  private AuthProvider authProvider;

  // L'ID unique fourni par le provider (ex: l'ID Google sub)
  @Column(name = "provider_id")
  private String providerId;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
