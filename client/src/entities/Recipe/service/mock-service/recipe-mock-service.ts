import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Recipe } from '../../model/recipe';
import mockData from '../../data/mock-recipes.json';
import { IRecipeService } from '../IRecipeService';

@Injectable({
  providedIn: 'root',
})
export class RecipeMockService implements IRecipeService {
  // Simulation d'un service de recettes utilisant des données mockées
  private recipesSubject: BehaviorSubject<Recipe[]> = new BehaviorSubject<Recipe[]>(mockData);
  private recipes$ = this.recipesSubject.asObservable();

  // Méthode pour obtenir toutes les recettes
  getAllRecipes(): Observable<Recipe[]> {
    return this.recipes$;
  }

  createRecipe(recipe: Recipe): Observable<Recipe> {
    const currentRecipes = this.recipesSubject.getValue();
    const newRecipe = { ...recipe, id: currentRecipes.length + 1 }; // Générer un ID unique simple
    const updatedRecipes = [...currentRecipes, newRecipe];
    this.recipesSubject.next(updatedRecipes);

    console.log('Mock service - Recipe created:', newRecipe);

    // Retourner l'observable de la nouvelle recette créée mais sans modifier le flux principal
    return of(newRecipe);
  }

  // delete(id: number): Observable<{ success: boolean }> {
  //   this.recipes = this.recipes.filter((r) => r.id !== id);
  //   return of({ success: true });
  // }
}
