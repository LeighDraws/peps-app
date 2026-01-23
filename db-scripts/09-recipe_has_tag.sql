-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS recipe_has_tag CASCADE;

-- Insertion de liaisons tags-recettes
INSERT INTO recipe_has_tag (id, recipe_id, tag_id, created_at, updated_at) VALUES
-- Poulet basquaise (recette 1) est "Facile" (tag 1)
(1, 1, 1, NOW(), NOW()),

-- Pâtes carbonara (recette 2) est "Facile" (tag 1)
(2, 2, 1, NOW(), NOW()),

-- Tarte au sucre (recette 3) est "Sucré" (tag 3) et "Facile" (tag 1)
(3, 3, 3, NOW(), NOW()),
(4, 3, 1, NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('recipe_has_tag', 'id'), (SELECT MAX(id) FROM recipe_has_tag), true);
