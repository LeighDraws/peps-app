-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS menu_has_recipe CASCADE;

-- Insertion de liaisons menus-recettes
INSERT INTO menu_has_recipe (id, menu_id, recipe_id, created_at, updated_at) VALUES
-- Le "Menu de la semaine" (menu 1) contient le Poulet basquaise (recette 1)
(1, 1, 1, NOW(), NOW()),

-- Le "Dîner Italien" (menu 2) contient les Pâtes carbonara (recette 2)
(2, 2, 2, NOW(), NOW()),

-- Le "Brunch du Dimanche" (menu 3) contient la Tarte au sucre (recette 3)
(3, 3, 3, NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('menu_has_recipe', 'id'), (SELECT MAX(id) FROM menu_has_recipe), true);
