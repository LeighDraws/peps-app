// import { test, expect } from '@playwright/test';

// test('test', async ({ page }) => {
//   await page.goto('http://localhost:4200/home');
//   await page.getByRole('textbox', { name: 'Nom de la recette' }).click();
//   await page.getByRole('textbox', { name: 'Nom de la recette' }).fill('Tarte aux poireaux');

//   await page.getByRole('textbox', { name: 'Description' }).click();
//   await page.getByRole('textbox', { name: 'Description' }).fill('Une tarte réconfortante!');

//   await page.getByText('Durée (minutes)').click();
//   await page.getByRole('spinbutton', { name: 'Durée (minutes)' }).fill('25');

//   await page.getByRole('textbox', { name: 'Ingrédients (séparés par des' }).click();
//   await page.getByRole('textbox', { name: 'Ingrédients (séparés par des' }).fill('p');
//   await page.getByRole('textbox', { name: 'Ingrédients (séparés par des' }).press('Dead');
//   await page
//     .getByRole('textbox', { name: 'Ingrédients (séparés par des' })
//     .fill('pâte brisée, poireaux, oeufs, crème');

//   await page.getByRole('button', { name: 'Ajouter' }).click();

//   await expect(page.getByRole('list')).toContainText('Tarte aux poireaux');
//   await expect(page.getByRole('list')).toContainText('Une tarte réconfortante!');
// });
