-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS menus CASCADE;

-- Insertion de 3 menus
INSERT INTO menus (menu_id, name, user_id, date, created_at, updated_at) VALUES
(1, 'Menu de la semaine', 1, '2026-01-26 12:00:00', NOW(), NOW()),
(2, 'Dîner Italien', 2, '2026-01-27 20:00:00', NOW(), NOW()),
(3, 'Brunch du Dimanche', 1, '2026-02-01 11:00:00', NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('menus', 'menu_id'), (SELECT MAX(menu_id) FROM menus), true);
