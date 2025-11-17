# Architecture (Feature-Sliced) — Diagramme

Ce dossier contient un schéma simplifié de l'architecture front basée sur le pattern Feature-Sliced Development.

Fichiers :
- `feature-sliced-diagram.svg` : diagramme vectoriel montrant les couches et le flux de dépendances.

Mapping rapide vers le projet `client` :

- **Pages / Routes** : `client/src/pages`
  - Conteneurs top-level qui composent les feature slices et déclarent les routes.

- **Features (slices)** : `client/src/features/*`
  - Unités fonctionnelles (ex. `recipe/recipe-form`, `recipes-list`) contenant composants, hooks, formulaires et tests.

- **Entities** : `client/src/entities/*`
  - Modèles métier et types (ex. `entities/Recipe/model`), mappers et utilitaires directement liés au domaine.

- **App / Services** : `client/src/app` et `client/src/app/Services`
  - Configuration globale, providers, services HTTP (ex. `HealthService`) et points d’intégration API.

- **Shared / UI** : `client/src/shared` (ou composants globaux sous `app` selon l’organisation)
  - Composants UI réutilisables, helpers, constantes, styles globaux.

- **Assets / Styles** : `client/src/assets`, `client/src/styles.css` et fichiers statiques
  - Images, polices, thèmes, fichiers CSS/Tailwind.

- **Pages** : `client/src/pages`
  - Pages top-level et routes (ex. `home.page`, `my-recipes.page`).

Bonnes pratiques suggérées :
- Respecter le flux de dépendance : Pages → Features → Entities → Shared/App.
- Ne pas faire dépendre les Entities de Features.
- Garder `app/Services` pour les appels réseau et la configuration partagée.

Ouvrir le diagramme :
- Dans VS Code, double-cliquez sur `client/architecture/feature-sliced-diagram.svg` pour l’aperçu.
- Vous pouvez aussi l’ouvrir dans un navigateur.

Souhaitez-vous :
- que je génère aussi un PNG exporté à côté du SVG, ou
- que j’ajoute des exemples de fichiers réels (liste de fichiers) pour chaque couche ?

## Exemple de structure par feature

Un exemple d'arborescence prêt à l'emploi est disponible dans :

`client/architecture/feature-structure.md`

Ouvrez ce fichier pour voir un arbre type (features / entities / shared / app / assets) et des recommandations d'organisation.
Le fichier a été mis à jour pour refléter que les modèles (`model`) sont placés dans `entities/recipe`, avec des dossiers `data`, `model` et `service`.
