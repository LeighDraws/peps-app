import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Recipe } from '../../model/recipe';
import mockData from '../../data/mock-recipes.json';
import { IRecipeService } from '../IRecipeService';

@Injectable({
  providedIn: 'root',
})
export class RecipeMockService implements IRecipeService {
  // Simulation d'un service de recettes utilisant des données mockées
  private recipes: Recipe[] = [...mockData] as Recipe[];

  // Méthode pour obtenir toutes les recettes
  getAllRecipes(): Observable<Recipe[]> {
    return of(this.recipes);
  }

  // create(recipe: Recipe): Observable<Recipe> {
  //   const newRecipe = { ...recipe, id: Date.now() };
  //   this.recipes.push(newRecipe);
  //   return of(newRecipe);
  // }

  // delete(id: number): Observable<{ success: boolean }> {
  //   this.recipes = this.recipes.filter((r) => r.id !== id);
  //   return of({ success: true });
  // }
}
