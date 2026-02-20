import { Routes } from '@angular/router';
import { RecipeListComponent } from 'src/features/recipe/recipes-list/recipe-list-component';
import { LoginPage } from 'src/pages/auth/login-page/login-page';
import { RegisterPage } from 'src/pages/auth/register-page/register-page';
import { HomePage } from 'src/pages/home.page/home-page';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', title: "Pep's | Home", component: HomePage },
  { path: 'recipes', title: "Pep's | Recipes", component: RecipeListComponent },
  { path: 'menus', title: "Pep's | Menus", component: HomePage },
  { path: 'profile', title: "Pep's | Profile", component: HomePage },
  { path: 'login', title: "Pep's | Login", component: LoginPage },
  { path: 'logout', title: "Pep's | Logout", component: HomePage },
  { path: 'register', title: "Pep's | Register", component: RegisterPage },
];
