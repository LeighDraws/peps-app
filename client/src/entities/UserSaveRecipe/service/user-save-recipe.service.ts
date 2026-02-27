import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map, Observable, tap } from 'rxjs';
import { SavedRecipeResponse } from '../model/saved-recipe-response';

@Injectable({
  providedIn: 'root',
})
export class UserSaveRecipeService {
  private readonly http = inject(HttpClient);
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/user-saved-recipes';

  // Signal pour stocker les IDs des recettes favorites
  private favoriteRecipeIdsWritable = signal<Set<number>>(new Set());
  public favoriteRecipeIds = this.favoriteRecipeIdsWritable.asReadonly();

  constructor() {
    this.loadFavoriteRecipes().subscribe();
  }

  /**
   * Récupère les recettes sauvegardées par l'utilisateur et met à jour le signal.
   */
  loadFavoriteRecipes(): Observable<SavedRecipeResponse[]> {
    return this.http.get<SavedRecipeResponse[]>(`${this.API_URL}${this.ENDPOINT}`).pipe(
      map((responses) => responses || []),
      tap((responses) => {
        const ids = responses.map((fav) => fav.recipeId);
        this.favoriteRecipeIdsWritable.set(new Set(ids));
      }),
    );
  }

  /**
   * Ajoute une recette aux favoris de l'utilisateur.
   */
  addFavorite(recipeId: number): Observable<void> {
    return this.http.post<void>(`${this.API_URL}${this.ENDPOINT}/${recipeId}`, {}).pipe(
      tap(() => {
        this.favoriteRecipeIdsWritable.update((currentIds) => {
          const newIds = new Set(currentIds);
          newIds.add(recipeId);
          return newIds;
        });
      }),
    );
  }

  /**
   * Supprime une recette des favoris de l'utilisateur.
   */
  removeFavorite(recipeId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${recipeId}`).pipe(
      tap(() => {
        this.favoriteRecipeIdsWritable.update((currentIds) => {
          const newIds = new Set(currentIds);
          newIds.delete(recipeId);
          return newIds;
        });
      }),
    );
  }
}
