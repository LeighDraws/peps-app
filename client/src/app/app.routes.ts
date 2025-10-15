import { Routes } from '@angular/router';
import { HomePage } from 'src/pages/home-page/home-page';

export const routes: Routes = [
  // {path: '', redirectTo: 'recipes', pathMatch: 'full'},
  { path: '', title: "Pep's | Home", component: HomePage },
];
