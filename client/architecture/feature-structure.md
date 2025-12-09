# Structure Feature-Sliced Desgin exemple

## Organisation :

```text
src/
  app/
  pages/
  widgets/
  features/
  entities/
  shared/
```

## FSD sépare clairement :

1️⃣ app/ — le bootstrap, la config \
Ex : déclaration des routes, initialisation Auth, global layout.

2️⃣ pages/ — l’entrée par route \
Ex : home.page, my-recipes.page

3️⃣ features/ — les interactions utilisateur avec logique métier \
Ex : recipe-form, recipe-list, filters, auth-login

4️⃣ entities/ — les modèles du domaine \
Ex : recipe, user, health-check

5️⃣ shared/ — bâtiment lego réutilisables\
Ex : button, layout, utils, styles

## Pourquoi on ne met pas tout dans app/ avec Angular ?

Parce qu’en FSD :

- app/ doit rester très léger
- features/, entities/, pages/ doivent vivre en dehors pour rester indépendants
- Ça évite les imports en spaghetti dans Angular
- Ça structure mieux les responsabilités

# Structure de Pep's en Feature-Sliced Design

Ce fichier illustre une organisation adaptée au projet Pep's en FSD. Le dossier `Services` dans `app` sert uniquement à checker la santé de l'application, il ne correspond pas à une logique métier et peut donc rester dans `app`.

```text

app/
└── layout/
    └── app-layout.html
    ├── app-layout.ts
    └── app-layout.css
└── Services/
    └── HealthService/
        └── health.service.ts

pages/
└── home.page/
    ├── home-page.ts
    ├── home-page.html
    └── home-page.css
└── my-recipes.page/

features/
└── recipes/
    ├── recipe-list/
    │   ├── recipe-list.component.ts
    │   ├── recipe-list.component.html
    │   └── recipe-list.component.css
    ├──  recipe-form/
    │    ├── recipe-form.component.ts
    │    ├── recipe-form.component.html
    │    └── recipe-form.component.css
└── menus/

entities/
└── recipe/
    ├── model/
    │   ├── recipe.types.ts
    │   └── recipe.mappers.ts
    ├── data/
    │   └── recipes.mock.json    # JSON temporaire pour données mockées
    └── service/
        ├── recipe.service.ts
        ├── recipe-api.service.ts
        ├── recipe-mock.service.ts
        ├── IRecipeService.ts
        └── service-config.ts

shared/
├── components/
│   └── button/
│       ├── button.component.ts
│       └── button.component.css
│   └── sidenav/
│   └── header/
├── utils/

assets/
└── fonts/
└── images/

```

Courte explication :

- `pages/` : pages top-level accessibles via routing (ex. `client/src/pages/*`).
- `features/recipes` : UI et logique spécifique à la feature (composants, hooks, petits wrappers locaux).
- `entities/recipe/model` : types et mappers de domaine (les modèles de données reçues de l'API).
- `entities/recipe/data` : JSON ou fixtures temporaires pour mocker les réponses (`recipes.mock.json`).
- `entities/recipe/service` : contrats et implémentations d'accès aux données (API, mock, adaptateurs) — `IRecipeService` + implémentations.
- `shared` : composants et utilitaires réutilisables entre features.
- `app/Services` : services d'application globaux (ex. `HealthService`).

Où placer dans ton projet :

- `client/src/pages` → Les pages, structures, pas de logiques métiers, pas de fonctionnel
- `client/src/features/*` → Tout élément qui correspond à une feature. Tout les composants en rapport avec `recipe` vont dans `/feature/recipe`. Composants d'affichage et pas de logique.
- `client/src/entities/*` → Les models, services et données qui correspondent à une entité. Le model d'une recette, son service d'appel à l'api et les données stockées : `/entities/Recipe`
- `client/src/shared/components` → Tout composant partagé dans l'application. `sidenav`, `header`, `button`, etc.
