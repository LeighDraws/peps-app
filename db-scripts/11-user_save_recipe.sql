-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS user_save_recipe CASCADE;

-- Insertion de liaisons utilisateurs-recettes sauvegardées
INSERT INTO user_save_recipe (id, user_id, recipe_id, created_at, updated_at) VALUES
-- "MamaRosa" (user 2) sauvegarde le "Poulet basquaise" (recette 1)
(1, 2, 1, NOW(), NOW()),

-- "ChefGusto" (user 1) sauvegarde les "Pâtes à la carbonara" (recette 2)
(2, 1, 2, NOW(), NOW()),

-- "SpiceMaster" (user 3) sauvegarde la "Tarte au sucre" (recette 3)
(3, 3, 3, NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('user_save_recipe', 'id'), (SELECT MAX(id) FROM user_save_recipe), true);
