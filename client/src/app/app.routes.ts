import { Routes } from '@angular/router';
import { RecipeListComponent } from 'src/features/recipe/recipes-list/recipe-list-component';
import { LoginPage } from 'src/pages/auth/login-page/login-page';
import { RegisterPage } from 'src/pages/auth/register-page/register-page';
import { HomePage } from 'src/pages/home.page/home-page';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', title: "Pep's | Accueil", component: HomePage },
  {
    path: 'recipes',
    title: "Pep's | Mes Recettes",
    component: RecipeListComponent,
    canActivate: [authGuard],
  },
  { path: 'menus', title: "Pep's | Mes Menus", component: HomePage, canActivate: [authGuard] },
  { path: 'profile', title: "Pep's | Mon Profil", component: HomePage, canActivate: [authGuard] },
  { path: 'login', title: "Pep's | Se Connecter", component: LoginPage },
  { path: 'register', title: "Pep's | S'inscrire", component: RegisterPage },
];
