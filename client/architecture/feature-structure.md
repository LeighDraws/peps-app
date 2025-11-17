# Exemple de structure (Feature-Sliced) — mise à jour

Ce fichier illustre une organisation adaptée à ton projet : le dossier `model` est placé dans `entities/recipe`, et `entities/recipe` contient `data`, `model` et `service`.

```text
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
├── utils/
└── styles/

app/
└── Services/
    └── HealthService/
        └── health.service.ts

assets/
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
- `client/src/pages` → `pages/`
- `client/src/features/*` → `features/`
- `client/src/entities/*` → `entities/`

Souhaites-tu que je génère des fichiers TypeScript d'exemple pour `IRecipeService` et `recipe.service.ts` ?
