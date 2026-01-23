-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS ingredients CASCADE;

-- Insertion de 3 ingrédients
INSERT INTO ingredients (id, name, category, created_at, updated_at) VALUES
(1, 'Poulet', 'MEAT', NOW(), NOW()),
(2, 'Tomate', 'VEGETABLE', NOW(), NOW()),
(3, 'Farine', 'CEREAL', NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('ingredients', 'id'), (SELECT MAX(id) FROM ingredients), true);
