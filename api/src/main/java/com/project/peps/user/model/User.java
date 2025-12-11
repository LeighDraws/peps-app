package com.project.peps.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String pseudo;

  @NotBlank(message = "L'email ne peut pas être vide.")
  @Email(
      message =
          "Le format de l'email est invalide.") // Validation du format de l'email par Hibernate
  // Validator
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
