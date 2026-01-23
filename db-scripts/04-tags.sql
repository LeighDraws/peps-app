-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS tags CASCADE;

-- Insertion de 3 tags
INSERT INTO tags (id, name, created_at, updated_at) VALUES
(1, 'Facile', NOW(), NOW()),
(2, 'Végétarien', NOW(), NOW()),
(3, 'Sucré', NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('tags', 'id'), (SELECT MAX(id) FROM tags), true);
