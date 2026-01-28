import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredient } from '../model/ingredient';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class IngredientService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/ingredients';

  private http = inject(HttpClient);

  getAllIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getIngredientById(id: number): Observable<Ingredient> {
    return this.http.get<Ingredient>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createIngredient(ingredient: Omit<Ingredient, 'id'>): Observable<Ingredient> {
    return this.http.post<Ingredient>(`${this.API_URL}${this.ENDPOINT}`, ingredient);
  }

  updateIngredient(id: number, ingredient: Ingredient): Observable<Ingredient> {
    return this.http.put<Ingredient>(`${this.API_URL}${this.ENDPOINT}/${id}`, ingredient);
  }

  deleteIngredient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
