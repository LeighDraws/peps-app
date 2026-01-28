-- Insertion de 3 utilisateurs réalistes
INSERT INTO users (user_id, pseudo, email, password, avatar_url, auth_provider, created_at, updated_at) VALUES
(1, 'ChefPepper', 'peps@example.com', 'password1', 'https://images.pexels.com/photos/3974516/pexels-photo-3974516.jpeg', 'LOCAL', NOW(), NOW()),
(2, 'MamaRosa', 'rosa@example.com', 'password2', 'https://images.pexels.com/photos/14472552/pexels-photo-14472552.jpeg', 'LOCAL', NOW(), NOW()),
(3, 'SpiceMaster', 'spice@example.com', 'password3', 'https://images.pexels.com/photos/10899488/pexels-photo-10899488.jpeg', 'GOOGLE', NOW(), NOW())
ON CONFLICT (user_id) DO NOTHING;

-- Insertion de 3 pays
INSERT INTO countries (id, name, image_url, created_at, updated_at) VALUES
(1, 'France', 'https://cdn-icons-png.flaticon.com/128/197/197560.png', NOW(), NOW()),
(2, 'Italie', 'https://www.flaticon.com/free-icon/italy_197626', NOW(), NOW()),
(3, 'Mexique', 'https://www.flaticon.com/free-icon/mexico_197397', NOW(), NOW()),
(4, 'Japon', 'https://www.flaticon.com/free-icon/japan_197604', NOW(), NOW()),
(5, 'Inde', 'https://www.flaticon.com/free-icon/india_197402', NOW(), NOW()),
(6, 'Chine', 'https://www.flaticon.com/free-icon/china_197375', NOW(), NOW()),
(7, 'Espagne', 'https://www.flaticon.com/free-icon/spain_197625', NOW(), NOW()),
(8, 'Grèce', 'https://www.flaticon.com/free-icon/greece_197606', NOW(), NOW()),
(9, 'Maroc', 'https://www.flaticon.com/free-icon/morocco_197405', NOW(), NOW()),
(10, 'Thaïlande', 'https://www.flaticon.com/free-icon/thailand_197418', NOW(), NOW()),
(11, 'Turquie', 'https://www.flaticon.com/free-icon/turkey_197599', NOW(), NOW()),
(12, 'Brésil', 'https://www.flaticon.com/free-icon/brazil_197386', NOW(), NOW()),
(13, 'Allemagne', 'https://www.flaticon.com/free-icon/germany_197571', NOW(), NOW()),
(14, 'États-Unis', 'https://www.flaticon.com/free-icon/united-states-of-america_197484', NOW(), NOW()),
(15, 'Royaume-Uni', 'https://www.flaticon.com/free-icon/united-kingdom_197485', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Insertion de 3 ingrédients
INSERT INTO ingredients (id, name, category, created_at, updated_at) VALUES
(1, 'Poulet', 'MEAT', NOW(), NOW()),
(2, 'Tomate', 'VEGETABLE', NOW(), NOW()),
(3, 'Farine', 'CEREAL', NOW(), NOW()),
(4, 'Sucre', 'SWEETENER', NOW(), NOW()),
(5, 'Œuf', 'DAIRY', NOW(), NOW()),
(6, 'Huile d''olive', 'OIL', NOW(), NOW()),
(7, 'Poivron', 'VEGETABLE', NOW(), NOW()),
(8, 'Oignon', 'VEGETABLE', NOW(), NOW()),
(9, 'Vin blanc', 'ALCOHOL', NOW(), NOW()),
(10, 'Guanciale', 'MEAT', NOW(), NOW()),
ON CONFLICT (id) DO NOTHING;

-- Insertion de 3 tags
INSERT INTO tags (id, name, created_at, updated_at) VALUES
(1, 'Réconfortant', NOW(), NOW()),
(2, 'Épicé', NOW(), NOW()),
(3, 'Umami', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Insertion de 3 recettes
INSERT INTO recipes (recipe_id, name, description, image_url, preparation_duration, category, price_range, difficulty, country_id, user_id, created_at, updated_at) VALUES
(1, 'Poulet basquaise', 'Un plat traditionnel du Pays basque.', 'https://recettes.precuttech.com/wp-content/uploads/2025/02/poulet-basquaise-cookeo-1.jpeg', 30, 'MEAT', 'NORMAL', 'EASY', 1, 1, NOW(), NOW()),
(2, 'Pâtes à la carbonara', 'La fameuse recette italienne.', 'https://www.gustini.fr/blog/wp-content/uploads/2024/02/rezept-41.jpg', 20, 'MEAT', 'CHEAP', 'EASY', 2, 2, NOW(), NOW()),
(3, 'Tarte au sucre', 'Un dessert simple et délicieux du Nord.', 'https://images.radio-canada.ca/v1/alimentation/recette/16x9/tarte-sucre-82143.jpg', 15, 'DESSERT', 'VERY_CHEAP', 'NORMAL', 1, 1, NOW(), NOW()),
(4, 'Poulet rôti', 'Un plat traditionnel du Pays basque.', 'https://www.apero-bordeaux.fr/wp-content/uploads/2024/02/20240216_65cfa1ce1fa54.jpg', 45, 'MEAT', 'NORMAL', 'NORMAL', 1, 1, NOW(), NOW()),
(5, 'Butter Chicker', 'La fameuse recette Indienne.', 'https://thegreenquest.org/fr/wp-content/uploads/sites/2/2024/02/butter-chicken-cauliglower_the-greenquest-3-sur-4.jpg', 25, 'MEAT', 'CHEAP', 'EASY', 2, 2, NOW(), NOW()),
(6, 'Tarte au citron', 'Un dessert.', 'https://assets.afcdn.com/recipe/20140121/63377_w1024h1024c1cx1100cy1412cxt0cyt0cxb2796cyb3567.webp', 20, 'DESSERT', 'VERY_CHEAP', 'NORMAL', 1, 1, NOW(), NOW())
ON CONFLICT (recipe_id) DO NOTHING;

-- Insertion des étapes pour les recettes
INSERT INTO steps (step_id, recipe_id, step_number, instruction, created_at, updated_at) VALUES
-- Poulet basquaise (recette 1)
(1, 1, 1, 'Découpez les poivrons et les oignons en lanières.', NOW(), NOW()),
(2, 1, 2, 'Faites dorer les morceaux de poulet dans une cocotte avec de l''huile d''olive.', NOW(), NOW()),
(3, 1, 3, 'Ajoutez les poivrons, les oignons, les tomates et le vin blanc. Laissez mijoter 30 minutes.', NOW(), NOW()),
(4, 1, 4, 'Assaisonnez avec du sel, du poivre et des herbes de Provence. Servez chaud.', NOW(), NOW()),
-- Pâtes carbonara (recette 2)
(5, 2, 1, 'Faites cuire les pâtes selon les instructions du paquet.', NOW(), NOW()),
(6, 2, 2, 'Pendant ce temps, coupez le guanciale en lardons et faites-le dorer à la poêle.', NOW(), NOW()),
(7, 2, 3, 'Dans un bol, mélangez les jaunes d''œufs, le pecorino, le sel et le poivre. Ajoutez les pâtes cuites et le guanciale. Servez immédiatement.', NOW(), NOW()),
-- Tarte au sucre (recette 3)
(8, 3, 1, 'Préparez la pâte et laissez-la lever.', NOW(), NOW()),
(9, 3, 2, 'Étalez la pâte dans un moule à tarte.', NOW(), NOW()),
(10, 3, 3, 'Garnissez avec du beurre et du sucre, puis enfournez à 180°C pendant 20 minutes.', NOW(), NOW()),
-- Poulet rôti (recette 4)
(6, 4, 1, 'Préchauffez le four à 200°C.', NOW(), NOW()),
(7, 4, 2, 'Assaisonnez le poulet avec du sel, du poivre et des herbes.', NOW(), NOW()),
(8, 4, 3, 'Placez le poulet dans un plat et enfournez pendant 45 minutes.', NOW(), NOW()),
(9, 4, 4, 'Servez chaud.', NOW(), NOW())
-- Pâtes carbonara (recette 2)
(4, 2, 1, 'Faites cuire les pâtes selon les instructions du paquet.', NOW(), NOW()),
(5, 2, 2, 'Pendant ce temps, coupez le guanciale en lardons et faites-le dorer à la poêle.', NOW(), NOW()),
(6, 2, 3, 'Dans un bol, mélangez les jaunes d''œufs, le pecorino, le sel et le poivre. Ajoutez les pâtes cuites et le guanciale. Servez immédiatement.', NOW(), NOW()),
-- Tarte au sucre (recette 3)
(7, 3, 1, 'Préparez la pâte et laissez-la lever.', NOW(), NOW()),
(8, 3, 2, 'Étalez la pâte dans un moule à tarte.', NOW(), NOW()),
(9, 3, 3, 'Garnissez avec du beurre et du sucre, puis enfournez à 180°C pendant 20 minutes.', NOW(), NOW())
ON CONFLICT (step_id) DO NOTHING;

-- Insertion de 3 menus
INSERT INTO menus (menu_id, name, user_id, date, created_at, updated_at) VALUES
(1, 'Menu de la semaine', 1, '2026-01-26 12:00:00', NOW(), NOW()),
(2, 'Dîner Italien', 2, '2026-01-27 20:00:00', NOW(), NOW()),
(3, 'Brunch du Dimanche', 1, '2026-02-01 11:00:00', NOW(), NOW())
ON CONFLICT (menu_id) DO NOTHING;

-- Insertion de liaisons ingrédients-recettes
INSERT INTO recipe_has_ingredient (id, recipe_id, ingredient_id, quantity, measurement_unit, created_at, updated_at) VALUES
-- Poulet basquaise (recette 1) utilise Poulet (ingrédient 1) et Tomate (ingrédient 2)
(1, 1, 1, 4, 'PIECE', NOW(), NOW()),
(2, 1, 2, 500, 'GRAM', NOW(), NOW()),

-- Pâtes carbonara (recette 2) utilise Farine (ingrédient 3) pour les pâtes fraîches
(3, 2, 3, 300, 'GRAM', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Insertion de liaisons tags-recettes
INSERT INTO recipe_has_tag (id, recipe_id, tag_id, created_at, updated_at) VALUES
-- Poulet basquaise (recette 1) est "Facile" (tag 1)
(1, 1, 1, NOW(), NOW()),

-- Pâtes carbonara (recette 2) est "Facile" (tag 1)
(2, 2, 1, NOW(), NOW()),

-- Tarte au sucre (recette 3) est "Sucré" (tag 3) et "Facile" (tag 1)
(3, 3, 3, NOW(), NOW()),
(4, 3, 1, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Insertion de liaisons menus-recettes
INSERT INTO menu_has_recipe (id, menu_id, recipe_id, created_at, updated_at) VALUES
-- Le "Menu de la semaine" (menu 1) contient le Poulet basquaise (recette 1)
(1, 1, 1, NOW(), NOW()),

-- Le "Dîner Italien" (menu 2) contient les Pâtes carbonara (recette 2)
(2, 2, 2, NOW(), NOW()),

-- Le "Brunch du Dimanche" (menu 3) contient la Tarte au sucre (recette 3)
(3, 3, 3, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Insertion de liaisons utilisateurs-recettes sauvegardées
INSERT INTO user_save_recipe (id, user_id, recipe_id, created_at, updated_at) VALUES
-- "MamaRosa" (user 2) sauvegarde le "Poulet basquaise" (recette 1)
(1, 2, 1, NOW(), NOW()),

-- "ChefGusto" (user 1) sauvegarde les "Pâtes à la carbonara" (recette 2)
(2, 1, 2, NOW(), NOW()),

-- "SpiceMaster" (user 3) sauvegarde la "Tarte au sucre" (recette 3)
(3, 3, 3, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('users', 'user_id'), (SELECT MAX(user_id) FROM users), true);
SELECT setval(pg_get_serial_sequence('countries', 'id'), (SELECT MAX(id) FROM countries), true);
SELECT setval(pg_get_serial_sequence('ingredients', 'id'), (SELECT MAX(id) FROM ingredients), true);
SELECT setval(pg_get_serial_sequence('tags', 'id'), (SELECT MAX(id) FROM tags), true);
SELECT setval(pg_get_serial_sequence('recipes', 'recipe_id'), (SELECT MAX(recipe_id) FROM recipes), true);
SELECT setval(pg_get_serial_sequence('steps', 'step_id'), (SELECT MAX(step_id) FROM steps), true);
SELECT setval(pg_get_serial_sequence('menus', 'menu_id'), (SELECT MAX(menu_id) FROM menus), true);
SELECT setval(pg_get_serial_sequence('recipe_has_ingredient', 'id'), (SELECT MAX(id) FROM recipe_has_ingredient), true);
SELECT setval(pg_get_serial_sequence('recipe_has_tag', 'id'), (SELECT MAX(id) FROM recipe_has_tag), true);
SELECT setval(pg_get_serial_sequence('menu_has_recipe', 'id'), (SELECT MAX(id) FROM menu_has_recipe), true);
SELECT setval(pg_get_serial_sequence('user_save_recipe', 'id'), (SELECT MAX(id) FROM user_save_recipe), true);
