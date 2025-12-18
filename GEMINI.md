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

- **Lombok** :
  - **DTOs** : `@Data` autorisé et encouragé.
  - **Entités** : ❌ `@Data` INTERDIT. Utiliser `@Getter`, `@Setter`, et `@ToString` (en excluant les relations Lazy).
  - **Constructeurs** : `@NoArgsConstructor` obligatoire pour JPA.
* **Injection** : Par constructeur uniquement (pas de `@Autowired` sur les champs).
* **Entités** : JPA pur, noms de tables explicites si nécessaire.
* **Controller** : Pas de logique métier, délègue immédiatement au Service.
* **DTO** : Systématiques pour les entrées/sorties d'API. Jamais d'entité JPA exposée directement.
* **Validation** : Utiliser `jakarta.validation` (`@Valid`, `@NotNull`, etc.) dans les DTOs.
* **Exceptions** : Les erreurs métier doivent lancer des exceptions personnalisées gérées par `GlobalExceptionHandler`.

---

## 💬 Convention de Nommage et Architecture (Service & Controller)

Respecter strictement les conventions suivantes pour garantir la cohérence avec les modules existants (ex: `User`) :

**1. Architecture Service :**
* Utiliser le pattern **Interface + Implémentation** (ex: `IngredientService` et `IngredientServiceImpl`).
* **Responsabilité :** Le Service manipule uniquement des **Entités**, jamais de DTOs (Request/Response).
* **Nommage des méthodes (Style Repository) :** Utiliser le préfixe `find` pour la lecture.
    * `findAll()`
    * `findById(Long id)`
    * `save(Entity entity)`
    * `update(Entity entity)`
    * `deleteById(Long id)`

**2. Architecture Controller :**
* **Responsabilité :** Le Controller gère la conversion Entité <-> DTO via le Mapper.
* **Nommage des méthodes (Style REST/Getter) :** Utiliser le préfixe `get` pour la lecture.
    * `getAllIngredients()`
    * `getIngredientById(Long id)`
    * `createIngredient(...)`
    * `updateIngredient(...)`
    * `deleteIngredientById(Long id)`


---

## 🗄 Base de données

* **SGBD** : PostgreSQL
* **Configuration** : `application.properties` charge les variables d'environnement (`DB_URL`, `DB_USER`).
* **DDL** : `spring.jpa.hibernate.ddl-auto=update` (en dev).
* **Docker** : La base tourne dans un conteneur nommé `peps-bdd`.

---

## 🧪 Tests backend

* **Unitaires** : JUnit 5 + Mockito (`spring-boot-starter-test`).
* **Focus** : Tester la logique des Services et les mappings.

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

---

## ✅ Attentes

* Proposer des solutions réalistes et compilables.
* Vérifier la compatibilité des versions (Java 21, Angular 20).
* Toujours penser cohérence backend ↔ frontend (ex: si on change le DTO Java, rappeler de mettre à jour l'interface TypeScript).

