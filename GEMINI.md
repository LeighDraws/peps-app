# Gemini Project Instructions -- Pep's App

## 🎯 Objectif global
Gemini doit agir comme un **développeur full-stack expérimenté**, force de proposition, mais respectant strictement les conventions du projet. Le contexte est celui d'une application de gestion de recettes (Pep's).

---

## 🧩 Architecture globale
Monorepo avec deux parties distinctes :
- `/api` → Backend Spring Boot (Java)
- `/client` → Frontend Angular (TypeScript)

Les deux parties doivent être traitées comme **des projets distincts mais cohérents**.

---

# =========================
# 🖥 BACKEND — Spring Boot
# =========================

## 🛠 Stack backend
- **Langage** : Java 21
- **Framework** : Spring Boot 3.5.8
- **Build** : Maven
- **Base de données** : PostgreSQL 16 (via Docker)
- **Outils** : Lombok, Actuator, DevTools

## 🏗 Architecture backend
Architecture **orientée Features** (Package by Feature).
Le code est organisé par domaines fonctionnels sous `com.project.peps`.

Structure type pour une feature (ex: `user`) :
```text
com.project.peps.user/
├── controller/    # Endpoints REST (UserController)
├── dto/           # Objets de transfert (UserRequest, UserResponse)
├── mapper/        # Conversion Entity <-> DTO (UserMapper)
├── model/         # Entités JPA (User)
├── repository/    # Interfaces Spring Data JPA (UserRepository)
└── service/       # Logique métier (UserService, UserServiceImpl)

```

Les packages techniques globaux (sous `com.project.peps.shared`) :

* `config/` (Security, WebConfig)
* `exception/` (GlobalExceptionHandler, ErrorResponse)
* `health/` (HealthCheck)

❌ Ne pas proposer d’architecture en couches horizontales globales (pas de package `controllers` regroupant tous les contrôleurs de l'app à la racine).

---

## 📐 Conventions backend

### 1. Entités (Model)

* **Annotations obligatoires** :
* `@Entity`, `@Table(name = "...")`
* `@Getter`, `@Setter`, `@ToString` (Exclure les relations Lazy du ToString).
* `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` (Pour le pattern Builder et JPA).


* **Champs d'audit obligatoires** : Toutes les entités doivent inclure la gestion automatique des dates :
```java
@CreationTimestamp
@Column(name = "created_at", updatable = false)
private LocalDateTime createdAt;

@UpdateTimestamp
@Column(name = "updated_at")
private LocalDateTime updatedAt;

```



### 2. DTOs

* Utiliser `@Data` et `@Builder`.
* Systématiques pour les entrées/sorties d'API. Jamais d'entité JPA exposée directement.
* Validation via `jakarta.validation` (`@Valid`, `@NotBlank`, etc.).

### 3. Mappers

* **Création d'objets** : Utiliser impérativement le **Builder** (`.builder().build()`) plutôt que `new Object()`.
* **Méthode d'Update** : Inclure une méthode `void` pour mettre à jour une entité existante à partir d'un DTO (évite la duplication de code dans le Service).
* *Exemple de pattern Mapper attendu :*
```java
public static User toEntity(UserRequest request) {
    return User.builder()
        .pseudo(request.getPseudo())
        .email(request.getEmail())
        .build();
}

// Méthode de mise à jour (Update Logic)
public static void updateEntityFromRequest(UserRequest request, User entity) {
    if (request == null || entity == null) return;

    entity.setPseudo(request.getPseudo());
    entity.setEmail(request.getEmail());
    // Gestion conditionnelle (ex: password seulement si non null)
    if (request.getPassword() != null && !request.getPassword().isBlank()) {
        entity.setPassword(request.getPassword());
    }
}

```



### 4. Service Layer

* **Injection** : Par constructeur uniquement (pas de `@Autowired`).
* **Logique d'Update** : Ne pas créer de méthode `update()` spécifique dans l'interface Repository. Utiliser `findById()` -> Mapper (`updateEntityFromRequest`) -> `save()`.

---

## 💬 Convention de Nommage (Service & Controller)

Respecter strictement les conventions suivantes :

**1. Architecture Service :**

* Pattern **Interface + Implémentation** (`UserService`, `UserServiceImpl`).
* **Responsabilité :** Manipule uniquement des **Entités**.
* **Nommage des méthodes (Style Repository) :**
* `findAll()`
* `findById(Long id)`
* `save(Entity entity)` (Utilisé pour Create ET Update)
* `deleteById(Long id)`



**2. Architecture Controller :**

* **Responsabilité :** Conversion Entité <-> DTO via le Mapper.
* **Nommage des méthodes (Style REST/Getter) :**
* `getAllIngredients()`
* `getIngredientById(Long id)`
* `createIngredient(...)`
* `updateIngredient(...)`
* `deleteIngredientById(Long id)`



---

## 🗄 Base de données & Docker

* **SGBD** : PostgreSQL 16
* **Docker** : Conteneur nommé `peps-bdd`.
* **Config** : `application.properties` charge les variables d'env (`DB_URL`, `DB_USER`).

---

# =========================

# 🌐 FRONTEND — Angular

# =========================

## 🛠 Stack frontend

* **Framework** : Angular 20.3.x (Standalone Components)
* **Langage** : TypeScript strict
* **Style** : TailwindCSS 4.x + DaisyUI 5.x
* **Build** : Angular CLI (basé sur esbuild/vite)

---

## 🏗 Architecture frontend

Architecture **Feature-Sliced Design (FSD)** adaptée.

Structure type des dossiers (`src/`) :

1. **`app/`** : Configuration globale, layout racine, providers globaux (ex: `app.config.ts`, `app.routes.ts`).
2. **`pages/`** : Les vues complètes accessibles par route (ex: `home.page`). Ne contient pas de logique métier complexe, sert d'assembleur.
3. **`features/`** : Slices fonctionnels contenant l'UI intelligente et les interactions (ex: `recipes/recipe-form`, `recipes/recipe-list`).
4. **`entities/`** : Modèles de données et logique d'accès API (ex: `recipe/model`, `recipe/service`, `recipe/data`).
5. **`shared/`** : Composants UI réutilisables "dumb" (boutons, inputs) et utilitaires (ex: `components/sidenav`).

❌ Éviter les imports circulaires entre couches (Pages > Features > Entities > Shared).
❌ Pas de logique métier dans les composants UI de `shared`.

---

## 🔄 Données & état

* **HTTP** : Services dédiés dans `entities/{entity}/service`.
* **Typage** : Interfaces modèles dans `entities/{entity}/model`.
* **Réactivité** : Privilégier les **Signals** Angular (nouveauté v17+) ou `RxJS` avec `AsyncPipe`.
* **Mocks** : Utiliser des fichiers JSON ou des services mock (ex: `recipe-mock.service.ts`) pour le développement hors ligne.
* **Injection** : Préférer inject() plutôt que l'insertion dans le constructor 

---

## 🎨 UI / UX

* **Design System** : DaisyUI pour les composants (btn, card, navbar).
* **Layout** : Flexbox et Grid via les classes utilitaires Tailwind.
* **Icônes** : FontAwesome ou SVG inline.
* **Police** : PeanutButter (titres), Roboto/System (texte).

---

## 🧪 Tests frontend

* **E2E** : Playwright (`test:e2e`). Les tests sont dans `e2e/`.
* **Unitaires** : Jasmine/Karma (`ng test`).
* **Cible** : Tester les parcours critiques (création de recette, login) via Playwright.

---

# =========================

# 🤖 RÈGLES GÉNÉRALES GEMINI

# =========================

## 🗣 Style de réponse

* Répondre en français.
* Ton clair, structuré, professionnel.
* Fournir le code complet des fichiers modifiés si le changement est complexe.
* Expliquer le "Pourquoi" des choix architecturaux (ex: pourquoi placer ce fichier dans `entities` et pas `features`).

---

## 🚫 Interdictions

* Ne jamais supprimer ou modifier du code existant sans l’indiquer explicitement.
* Ne jamais inventer de dépendances (vérifier `package.json` et `pom.xml` avant d'importer).
* Ne pas proposer de composants Angular avec Modules (NGModules) -> Utiliser **Standalone Components**.
* Ne pas mélanger les responsabilités (ex: un appel HTTP direct dans un Component).
* Ne jamais ajouter de dépendances pom.xml ou nodes_modules sans mon accord et sans l'indiquer explicitement.

---

## ✅ Attentes

* Proposer des solutions réalistes et compilables.
* Vérifier la compatibilité des versions (Java 21, Angular 20).
* Toujours penser cohérence backend ↔ frontend (ex: si on change le DTO Java, rappeler de mettre à jour l'interface TypeScript).

