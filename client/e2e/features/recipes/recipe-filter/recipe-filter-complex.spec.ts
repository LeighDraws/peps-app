import { test, expect } from '@playwright/test';

test.use({ baseURL: 'http://localhost:4200' });

// ** TEST GENERE PAR GEMINI **

const MOCK_INGREDIENTS = [
  { id: 1, name: 'Tomate' },
  { id: 2, name: 'Oignon' },
  { id: 3, name: 'Basilic' },
  { id: 4, name: 'Fromage' },
  { id: 5, name: 'Poulet' },
];

const MOCK_RECIPES = [
  {
    id: 1,
    name: 'Pizza Margherita',
    description: 'Classique italienne',
    ingredients: [{ id: 1 }, { id: 3 }, { id: 4 }],
    imageUrl: 'pizza.jpg',
    category: 'VEGETARIAN',
    country: { id: 1, name: 'Italie' },
  },
  {
    id: 2,
    name: 'Salade Caprese', // <--- Correction ici
    description: 'Salade fraîche',
    ingredients: [{ id: 1 }, { id: 3 }],
    imageUrl: 'caprese.jpg',
    category: 'VEGETARIAN',
    country: { id: 1, name: 'Italie' },
  },
  {
    id: 3,
    name: 'Poulet Curry',
    description: 'Plat indien épicé',
    ingredients: [{ id: 5 }, { id: 2 }], // Poulet, Oignon
    imageUrl: 'curry.jpg',
    category: 'NON_VEGETARIAN',
    country: { id: 2, name: 'Inde' },
  },
  {
    id: 4,
    name: "Soupe à l'Oignon",
    description: 'Soupe française réconfortante',
    ingredients: [{ id: 2 }], // Oignon
    imageUrl: 'soupe.jpg',
    category: 'VEGETARIAN',
    country: { id: 1, name: 'France' },
  },
];

test.beforeEach(async ({ page }) => {
  // Mock API calls for ingredients
  await page.route('**/api/ingredients', async (route) => {
    // Check if it's a search request
    const url = new URL(route.request().url());
    const searchTerm = url.searchParams.get('searchTerm');
    if (searchTerm) {
      const filtered = MOCK_INGREDIENTS.filter((ing) =>
        ing.name.toLowerCase().includes(searchTerm.toLowerCase()),
      );
      await route.fulfill({
        status: 200,
        contentType: 'application/json',
        body: JSON.stringify(filtered),
      });
    } else {
      await route.fulfill({
        status: 200,
        contentType: 'application/json',
        body: JSON.stringify(MOCK_INGREDIENTS),
      });
    }
  });

  // Mock API calls for recipes
  await page.route('**/api/recipes*', async (route) => {
    const url = new URL(route.request().url());
    console.log('Interception recette:', route.request().url());
    const includedIngredientIds = url.searchParams.getAll('includedIngredientIds');
    const excludedIngredientIds = url.searchParams.getAll('excludedIngredientIds');

    let filteredRecipes = MOCK_RECIPES;

    if (includedIngredientIds.length > 0) {
      filteredRecipes = filteredRecipes.filter((recipe) =>
        includedIngredientIds.every((id) =>
          recipe.ingredients.some((ing) => ing.id === parseInt(id, 10)),
        ),
      );
    }

    if (excludedIngredientIds.length > 0) {
      filteredRecipes = filteredRecipes.filter(
        (recipe) =>
          !excludedIngredientIds.some((id) =>
            recipe.ingredients.some((ing) => ing.id === parseInt(id, 10)),
          ),
      );
    }

    await route.fulfill({
      status: 200,
      contentType: 'application/json',
      body: JSON.stringify(filteredRecipes),
    });
  });
});

test('should filter recipes by included and excluded ingredients (TASTE filter)', async ({
  page,
}) => {
  await page.goto('/home');

  // 1. Initial state: All recipes should be visible
  await expect(page.getByText('Pizza Margherita')).toBeVisible();
  await expect(page.getByText('Salade Caprese')).toBeVisible();
  await expect(page.getByText('Poulet Curry')).toBeVisible();
  await expect(page.getByText("Soupe à l'Oignon")).toBeVisible();

  // Open "Goûts" filter modal
  await page.getByRole('button', { name: 'Goûts' }).click();
  await expect(page.getByText('Choix des goûts')).toBeVisible();

  // Test Case 1: Include 'Tomate' (ID: 1)
  await page.getByPlaceholder('Rechercher un ingrédient...').fill('Tomate');
  await page.getByRole('button', { name: 'Tomate' }).click(); // Click to INCLUDE Tomate
  await expect(page.getByRole('button', { name: 'Tomate ✅' })).toBeVisible(); // Verify it's included

  await page.getByRole('button', { name: 'Appliquer' }).click();
  await expect(page.getByText('Pizza Margherita')).toBeVisible();
  await expect(page.getByText('Salade Caprese')).toBeVisible();
  await expect(page.getByText('Poulet Curry')).toBeHidden();
  await expect(page.getByText("Soupe à l'Oignon")).toBeHidden();

  // Re-open "Goûts" filter modal
  await page.getByRole('button', { name: 'Goûts' }).click();
  await expect(page.getByText('Choix des goûts')).toBeVisible();

  // Test Case 2: Exclude 'Fromage' (ID: 4) while 'Tomate' is still included
  await page.getByPlaceholder('Rechercher un ingrédient...').fill('Fromage');
  await page.getByRole('button', { name: 'Fromage' }).click(); // Click to INCLUDE Fromage
  await expect(page.getByRole('button', { name: 'Fromage ✅' })).toBeVisible();
  await page.getByRole('button', { name: 'Fromage ✅' }).click(); // Click again to EXCLUDE Fromage
  await expect(page.getByRole('button', { name: 'Fromage ❌' })).toBeVisible();

  await page.getByRole('button', { name: 'Appliquer' }).click();
  // Expect Salade Caprese (Tomate, Basilic)
  await expect(page.getByText('Pizza Margherita')).toBeHidden(); // Excludes Fromage
  await expect(page.getByText('Salade Caprese')).toBeVisible();
  await expect(page.getByText('Poulet Curry')).toBeHidden();
  await expect(page.getByText("Soupe à l'Oignon")).toBeHidden();

  // Re-open "Goûts" filter modal
  await page.getByRole('button', { name: 'Goûts' }).click();
  await expect(page.getByText('Choix des goûts')).toBeVisible();

  // Test Case 3: Clear 'Tomate' (ID: 1) filter by clicking it a third time
  await page.getByRole('button', { name: 'Tomate ✅' }).click(); // Change to EXCLUDED
  await expect(page.getByRole('button', { name: 'Tomate ❌' })).toBeVisible();
  await page.getByRole('button', { name: 'Tomate ❌' }).click(); // Remove completely

  // Clear 'Fromage' (ID: 4) filter as well
  await page.getByRole('button', { name: 'Fromage ❌' }).click(); // Remove completely

  // Check that selected ingredients are empty
  await expect(page.getByText('Aucun ingrédient sélectionné.')).toBeVisible();

  await page.getByRole('button', { name: 'Appliquer' }).click();
  // Expect all recipes to be visible again
  await expect(page.getByText('Pizza Margherita')).toBeVisible();
  await expect(page.getByText('Salade Caprese')).toBeVisible();
  await expect(page.getByText('Poulet Curry')).toBeVisible();
  await expect(page.getByText("Soupe à l'Oignon")).toBeVisible();
});

test('should filter recipes by excluding an ingredient', async ({ page }) => {
  await page.goto('/home');

  // Open "Goûts" filter modal
  await page.getByRole('button', { name: 'Goûts' }).click();
  await expect(page.getByText('Choix des goûts')).toBeVisible();

  // Test Case: Exclude 'Oignon' (ID: 2)
  await page.getByPlaceholder('Rechercher un ingrédient...').fill('Oignon');
  await page.getByRole('button', { name: 'Oignon' }).click(); // Click to INCLUDE Oignon
  await expect(page.getByRole('button', { name: 'Oignon ✅' })).toBeVisible();
  await page.getByRole('button', { name: 'Oignon ✅' }).click(); // Click again to EXCLUDE Oignon
  await expect(page.getByRole('button', { name: 'Oignon ❌' })).toBeVisible();

  await page.getByRole('button', { name: 'Appliquer' }).click();
  // Expect only recipes without Oignon
  await expect(page.getByText('Pizza Margherita')).toBeVisible();
  await expect(page.getByText('Salade Caprese')).toBeVisible();
  await expect(page.getByText('Poulet Curry')).toBeHidden();
  await expect(page.getByText("Soupe à l'Oignon")).toBeHidden();
});
