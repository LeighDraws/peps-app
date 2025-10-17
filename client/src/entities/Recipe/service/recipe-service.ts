import { inject, Injectable } from '@angular/core';
import { IRecipeService } from './IRecipeService';
import { RecipeMockService } from './mock-service/recipe-mock-service';
import { USE_MOCK } from './config';
import { RecipeApiService } from './api-service/recipe-api-service';
import { Observable } from 'rxjs';
import { Recipe } from '../model/recipe';

@Injectable({
  providedIn: 'root',
})
export class RecipeService implements IRecipeService {
  private service: IRecipeService;
  private mockService = inject(RecipeMockService);
  private apiService = inject(RecipeApiService);

  constructor() {
    // Choisir le service à utiliser (mock ou API)
    this.service = USE_MOCK ? this.mockService : this.apiService;
  }

  getAllRecipes(): Observable<Recipe[]> {
    return this.service.getAllRecipes();
  }

  createRecipe(recipe: Recipe): Observable<Recipe> {
    return this.service.createRecipe(recipe);
  }

  // create(recipe: Recipe): Observable<Recipe> {
  //   return this.service.create(recipe);
  // }

  // delete(id: number): Observable<{ success: boolean }> {
  //   return this.service.delete(id);
  // }
}
