-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS recipe_has_ingredient CASCADE;

-- Insertion de liaisons ingrédients-recettes
INSERT INTO recipe_has_ingredient (id, recipe_id, ingredient_id, quantity, measurement_unit, created_at, updated_at) VALUES
-- Poulet basquaise (recette 1) utilise Poulet (ingrédient 1) et Tomate (ingrédient 2)
(1, 1, 1, 4, 'PIECE', NOW(), NOW()),
(2, 1, 2, 500, 'GRAM', NOW(), NOW()),

-- Pâtes carbonara (recette 2) utilise Farine (ingrédient 3) pour les pâtes fraîches
(3, 2, 3, 300, 'GRAM', NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('recipe_has_ingredient', 'id'), (SELECT MAX(id) FROM recipe_has_ingredient), true);
