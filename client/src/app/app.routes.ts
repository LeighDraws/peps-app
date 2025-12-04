import { Routes } from '@angular/router';
import { RecipeListComponent } from 'src/features/recipe/recipes-list/recipe-list-component';
import { HomePage } from 'src/pages/home.page/home-page';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', title: "Pep's | Home", component: HomePage },
  { path: 'recipes', title: "Pep's | Recipes", component: RecipeListComponent },
  { path: 'menus', title: "Pep's | Menus", component: HomePage },
  { path: 'settings', title: "Pep's | Settings", component: HomePage },
  { path: 'logout', title: "Pep's | Logout", component: HomePage },
];
