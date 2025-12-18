import { Observable } from 'rxjs';
import { Recipe } from '../model/recipe';

export interface IRecipeService {
  // Interface du service Recipe qui definit les methodes a implementer

  getAllRecipes(): Observable<Recipe[]>;
  createRecipe(recipe: Recipe): Observable<Recipe>;
  // delete(id: number): Observable<{ success: boolean }>;
}
