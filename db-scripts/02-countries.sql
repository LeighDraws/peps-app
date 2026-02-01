-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS countries CASCADE;

-- Insertion de 3 pays
INSERT INTO countries (id, name, image_url, created_at, updated_at) VALUES
(1, 'France', 'https://cdn-icons-png.flaticon.com/128/197/197560.png', NOW(), NOW()),
(2, 'Italie', 'https://www.flaticon.com/free-icon/italy_197626', NOW(), NOW()),
(3, 'Mexique', 'https://www.flaticon.com/free-icon/mexico_197397', NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('countries', 'id'), (SELECT MAX(id) FROM countries), true);
