import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../model/recipe';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/recipes';

  private http = inject(HttpClient);

  getAllRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.API_URL}${this.ENDPOINT}`);
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
