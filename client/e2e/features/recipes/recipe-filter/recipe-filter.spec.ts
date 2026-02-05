import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('http://localhost:4200/home');

  // Filtrer par "Dessert"
  await page.getByRole('button', { name: 'Régime Alimentaire' }).click();
  await page.getByRole('checkbox', { name: 'Dessert' }).check();
  await page.getByRole('button', { name: 'Appliquer' }).click();

  // On attend que "Tarte au sucre" (un dessert) soit visible
  await expect(page.getByText('Tarte au sucre')).toBeVisible();
  // On s'attend à ce que "Butter Chicken" (un plat principal) ne soit pas visible
  await expect(page.getByText('Butter Chicken')).toBeHidden();

  // Filtrer par "Pays" -> "Inde"
  await page.getByRole('button', { name: 'Pays' }).click();
  await page.getByRole('checkbox', { name: 'Inde' }).check();
  await page.getByRole('button', { name: 'Appliquer' }).click();

  // On s'attend à ce que "Butter Chicken" (un plat indien) soit visible
  await expect(page.locator('app-recipe-card').getByText('Butter Chicken')).toBeVisible();
  // "Tarte au sucre" non visible
  await expect(page.getByText('Tarte au sucre')).toBeHidden();
});
