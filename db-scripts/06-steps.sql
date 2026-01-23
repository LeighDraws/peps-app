-- Supprime la table si elle existe déjà
DROP TABLE IF EXISTS steps CASCADE;

-- Insertion des étapes pour les recettes
INSERT INTO steps (step_id, recipe_id, step_number, instruction, created_at, updated_at) VALUES
-- Poulet basquaise (recette 1)
(1, 1, 1, 'Découpez les poivrons et les oignons en lanières.', NOW(), NOW()),
(2, 1, 2, 'Faites dorer les morceaux de poulet dans une cocotte avec de l''huile d''olive.', NOW(), NOW()),
(3, 1, 3, 'Ajoutez les poivrons, les oignons, les tomates et le vin blanc. Laissez mijoter 30 minutes.', NOW(), NOW()),

-- Pâtes carbonara (recette 2)
(4, 2, 1, 'Faites cuire les pâtes selon les instructions du paquet.', NOW(), NOW()),
(5, 2, 2, 'Pendant ce temps, coupez le guanciale en lardons et faites-le dorer à la poêle.', NOW(), NOW()),
(6, 2, 3, 'Dans un bol, mélangez les jaunes d''œufs, le pecorino, le sel et le poivre. Ajoutez les pâtes cuites et le guanciale. Servez immédiatement.', NOW(), NOW()),

-- Tarte au sucre (recette 3)
(7, 3, 1, 'Préparez la pâte et laissez-la lever.', NOW(), NOW()),
(8, 3, 2, 'Étalez la pâte dans un moule à tarte.', NOW(), NOW()),
(9, 3, 3, 'Garnissez avec du beurre et du sucre, puis enfournez à 180°C pendant 20 minutes.', NOW(), NOW());

-- Gestion des IDs
SELECT setval(pg_get_serial_sequence('steps', 'step_id'), (SELECT MAX(step_id) FROM steps), true);
