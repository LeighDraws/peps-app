import { Routes } from '@angular/router';
import { RecipeListComponent } from 'src/features/recipe/recipes-list/recipe-list-component';
import { HomePage } from 'src/pages/home.page/home-page';

export const routes: Routes = [
  // {path: '', redirectTo: 'recipes', pathMatch: 'full'},
  { path: '', title: "Pep's | Home", component: HomePage },
  { path: 'recipes', title: "Pep's | Recipes", component: RecipeListComponent },
];
