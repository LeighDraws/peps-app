import { test, expect } from '@playwright/test';

// Données mockées
const mockCountries = [
  { id: 1, name: 'France', code: 'FR' },
  { id: 2, name: 'Inde', code: 'IN' },
];

const mockRecipes = [
  {
    id: 1,
    name: 'Tarte au sucre',
    category: 'DESSERT',
    country: { id: 1, name: 'France' },
  },
  {
    id: 2,
    name: 'Butter Chicken',
    category: 'MEAT',
    country: { id: 2, name: 'Inde' },
  },
];

test('Filtre des recettes (Mocked API)', async ({ page }) => {
  // 2. Interception des appels API AVANT de charger la page

  // A. Intercepter la liste des pays (pour la modale de filtre)
  await page.route('**/api/countries', async (route) => {
    await route.fulfill({ json: mockCountries });
  });

  // B. Intercepter l'appel par défaut (toutes les recettes)
  // On utilise une Regex ou un pattern simple.
  await page.route('**/api/recipes', async (route) => {
    // Si l'URL contient des paramètres de filtre, on ne répond pas ici (la suite le gérera)
    // ou on gère tout ici. Pour faire simple, Playwright prend la première route qui match.
    // Ici, on va être plus précis pour les filtres.
    await route.fulfill({ json: mockRecipes });
  });

  // C. Intercepter le filtre CATEGORY = DESSERT
  await page.route('**/api/recipes?*category=DESSERT*', async (route) => {
    const desserts = mockRecipes.filter((r) => r.category === 'DESSERT');
    await route.fulfill({ json: desserts });
  });

  // D. Intercepter le filtre COUNTRY = INDE (id 2)
  // Adapte le paramètre selon ce que ton front envoie vraiment (ex: countryId=2)
  await page.route('**/api/recipes?*countryId=2*', async (route) => {
    const indianFood = mockRecipes.filter((r) => r.country.id === 2);
    await route.fulfill({ json: indianFood });
  });

  // 3. Lancer le scénario utilisateur
  await page.goto('http://localhost:4200/home');

  // --- Test Filtre DESSERT ---
  await page.getByRole('button', { name: 'Régime Alimentaire' }).click();
  await page.getByRole('checkbox', { name: 'Dessert' }).check();
  await page.getByRole('button', { name: 'Appliquer' }).click();

  // Playwright va intercepter l'appel réseau et renvoyer uniquement la Tarte au sucre
  await expect(page.getByText('Tarte au sucre')).toBeVisible();
  await expect(page.getByText('Butter Chicken')).toBeHidden();

  // --- Test Filtre PAYS (Reset ou changement) ---re :

  await page.getByRole('button', { name: 'Pays' }).click();

  // La liste des pays vient de notre mockCountries !
  await page.getByRole('checkbox', { name: 'Inde' }).check();
  await page.getByRole('button', { name: 'Appliquer' }).click();

  // Playwright intercepte l'appel countryId=2
  await expect(page.locator('app-recipe-card').getByText('Butter Chicken')).toBeVisible();
  await expect(page.getByText('Tarte au sucre')).toBeHidden();
});
