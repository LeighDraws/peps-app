import { inject, Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IRecipeService } from '../IRecipeService';
import { Observable } from 'rxjs';
import { Recipe } from '../../model/recipe';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RecipeApiService implements IRecipeService {
  private readonly API_URL: string = environment.apiUrl;
  // TODO: Vérifier l'endpoint avec le backend plus tard
  private readonly ENDPOINT = '/api/recipes';

  private http = inject(HttpClient);

  getAllRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.API_URL}${this.ENDPOINT}`);
  }
}
