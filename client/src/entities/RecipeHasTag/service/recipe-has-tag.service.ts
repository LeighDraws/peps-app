import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RecipeHasTag } from '../model/recipe-has-tag';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RecipeHasTagService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/recipe-has-tags';

  private http = inject(HttpClient);

  getAllRecipeHasTags(): Observable<RecipeHasTag[]> {
    return this.http.get<RecipeHasTag[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getRecipeHasTagById(id: number): Observable<RecipeHasTag> {
    return this.http.get<RecipeHasTag>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createRecipeHasTag(recipeHasTag: Omit<RecipeHasTag, 'id'>): Observable<RecipeHasTag> {
    return this.http.post<RecipeHasTag>(`${this.API_URL}${this.ENDPOINT}`, recipeHasTag);
  }

  updateRecipeHasTag(id: number, recipeHasTag: RecipeHasTag): Observable<RecipeHasTag> {
    return this.http.put<RecipeHasTag>(`${this.API_URL}${this.ENDPOINT}/${id}`, recipeHasTag);
  }

  deleteRecipeHasTag(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
