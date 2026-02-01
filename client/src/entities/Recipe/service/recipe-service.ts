import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
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

  getAllRecipes(filters?: RecipeFilters): Observable<Recipe[]> {
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
