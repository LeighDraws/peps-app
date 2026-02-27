import { Routes } from '@angular/router';
import { LoginPage } from 'src/pages/auth/login-page/login-page';
import { RegisterPage } from 'src/pages/auth/register-page/register-page';
import { HomePage } from 'src/pages/home.page/home-page';
import { ProfilePage } from 'src/pages/profile.page/profile-page';
import { authGuard } from './guards/auth-guard';
import { MyRecipesPageComponent } from 'src/pages/my-recipes/my-recipes.page';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', title: "Pep's | Accueil", component: HomePage },
  {
    path: 'recipes',
    title: "Pep's | Mes Recettes",
    component: MyRecipesPageComponent,
    canActivate: [authGuard],
  },
  { path: 'menus', title: "Pep's | Mes Menus", component: HomePage, canActivate: [authGuard] },
  {
    path: 'profile',
    title: "Pep's | Mon Profil",
    component: ProfilePage,
    canActivate: [authGuard],
  },
  { path: 'login', title: "Pep's | Se Connecter", component: LoginPage },
  { path: 'register', title: "Pep's | S'inscrire", component: RegisterPage },
];
