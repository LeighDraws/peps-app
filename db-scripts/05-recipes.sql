-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS recipes CASCADE;

-- Insertion de 3 recettes
INSERT INTO recipes (recipe_id, name, description, preparation_duration, category, price_range, difficulty, country_id, user_id, created_at, updated_at) VALUES
(1, 'Poulet basquaise', 'Un plat traditionnel du Pays basque.', 30, 'MEAT', 'NORMAL', 'EASY', 1, 1, NOW(), NOW()),
(2, 'Pâtes à la carbonara', 'La fameuse recette italienne.', 20, 'MEAT', 'CHEAP', 'EASY', 2, 2, NOW(), NOW()),
(3, 'Tarte au sucre', 'Un dessert simple et délicieux du Nord.', 15, 'DESSERT', 'VERY_CHEAP', 'NORMAL', 1, 1, NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('recipes', 'recipe_id'), (SELECT MAX(recipe_id) FROM recipes), true);
