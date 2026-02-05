import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable, signal, WritableSignal } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { RecipeFilters } from '../model/recipe-filters';
import { Recipe } from '../model/recipe';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/recipes';

  private http = inject(HttpClient);

  // Création d'un signal pour stocker la liste des recettes (un signal modifiable seulement pour le service et un signal en lecture seule pour les autres composants)
  private _recipes: WritableSignal<Recipe[]> = signal([]);
  public readonly recipes = this._recipes.asReadonly();

  // Méthode privée pour récupérer toutes les recettes avec des filtres optionnels, ne peut pas être appelée depuis l'extérieur du service
  private _getAllRecipes(filters?: RecipeFilters): Observable<Recipe[]> {
    let params = new HttpParams();
    if (filters) {
      if (filters.countryId) {
        params = params.append('countryId', filters.countryId.toString());
      }
      if (filters.category) {
        params = params.append('category', filters.category);
      }
      if (filters.includedIngredientIds) {
        filters.includedIngredientIds.forEach((id) => {
          params = params.append('includedIngredientIds', id.toString());
        });
      }
      if (filters.excludedIngredientIds) {
        filters.excludedIngredientIds.forEach((id) => {
          params = params.append('excludedIngredientIds', id.toString());
        });
      }
    }
    return this.http.get<Recipe[]>(`${this.API_URL}${this.ENDPOINT}`, {
      params,
    });
  }

  // Méthode publique pour charger toutes les recettes avec des filtres optionnels et mettre à jour le signal interne
  loadRecipes(filters?: RecipeFilters): void {
    this._getAllRecipes(filters).subscribe({
      next: (recipes) => this._recipes.set(recipes),
      error: (err) => console.error('Error loading recipes:', err),
    });
  }

  getRecipeById(id: string): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createRecipe(recipe: Omit<Recipe, 'id'>): Observable<Recipe> {
    return this.http.post<Recipe>(`${this.API_URL}${this.ENDPOINT}`, recipe);
  }

  updateRecipe(id: string, recipe: Recipe): Observable<Recipe> {
    return this.http.put<Recipe>(`${this.API_URL}${this.ENDPOINT}/${id}`, recipe);
  }

  deleteRecipe(id: string): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
