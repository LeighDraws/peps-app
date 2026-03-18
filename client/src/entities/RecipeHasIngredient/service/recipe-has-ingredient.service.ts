import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RecipeHasIngredient, RecipeHasIngredientResponse } from '../model/recipe-has-ingredient';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RecipeHasIngredientService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/recipe-ingredients';

  private http = inject(HttpClient);

  getAllRecipeHasIngredients(): Observable<RecipeHasIngredient[]> {
    return this.http.get<RecipeHasIngredient[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getRecipeHasIngredientById(id: number): Observable<RecipeHasIngredient> {
    return this.http.get<RecipeHasIngredient>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  getIngredientsByRecipeId(recipeId: number): Observable<RecipeHasIngredientResponse[]> {
    return this.http.get<RecipeHasIngredientResponse[]>(
      `${this.API_URL}${this.ENDPOINT}/recipe/${recipeId}`,
    );
  }

  createRecipeHasIngredient(
    recipeHasIngredient: Omit<RecipeHasIngredient, 'id'>,
  ): Observable<RecipeHasIngredient> {
    return this.http.post<RecipeHasIngredient>(
      `${this.API_URL}${this.ENDPOINT}`,
      recipeHasIngredient,
    );
  }

  updateRecipeHasIngredient(
    id: number,
    recipeHasIngredient: RecipeHasIngredient,
  ): Observable<RecipeHasIngredient> {
    return this.http.put<RecipeHasIngredient>(
      `${this.API_URL}${this.ENDPOINT}/${id}`,
      recipeHasIngredient,
    );
  }

  deleteRecipeHasIngredient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
