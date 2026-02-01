-- Supprime la table si elle existe déjà pour repartir de zéro
DROP TABLE IF EXISTS users CASCADE;

-- Insertion de 3 utilisateurs réalistes
INSERT INTO users (user_id, pseudo, email, password, avatar_url, auth_provider, created_at, updated_at) VALUES
(1, 'ChefPepper', 'peps@example.com', 'password1', 'https://images.pexels.com/photos/3974516/pexels-photo-3974516.jpeg', 'LOCAL', NOW(), NOW()),
(2, 'MamaRosa', 'rosa@example.com', 'password2', 'https://images.pexels.com/photos/14472552/pexels-photo-14472552.jpeg', 'LOCAL', NOW(), NOW()),
(3, 'SpiceMaster', 'spice@example.com', 'password3', 'https://images.pexels.com/photos/10899488/pexels-photo-10899488.jpeg', 'GOOGLE', NOW(), NOW());


SELECT setval(pg_get_serial_sequence('users', 'user_id'), (SELECT MAX(user_id) FROM users), true);
