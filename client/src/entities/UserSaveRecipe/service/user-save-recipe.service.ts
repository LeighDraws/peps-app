import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map, Observable, tap } from 'rxjs';
import { SavedRecipeResponse } from '../model/saved-recipe-response';
import { AuthService } from 'src/entities/User/service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserSaveRecipeService {
  private readonly http = inject(HttpClient);
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/user-saved-recipes';

  private authService = inject(AuthService);
  private user = 0;

  // Signal pour stocker les IDs des recettes favorites
  private favoriteRecipeIdsWritable = signal<Set<number>>(new Set());
  public favoriteRecipeIds = this.favoriteRecipeIdsWritable.asReadonly();

  constructor() {
    this.loadFavoriteRecipes(this.user).subscribe();
    this.authService.getCurrentUser().subscribe((user) => {
      if (user) {
        this.user = user.id;
        this.loadFavoriteRecipes(this.user).subscribe();
      }
    });
  }

  /**
   * Récupère les recettes sauvegardées par l'utilisateur et met à jour le signal.
   */
  loadFavoriteRecipes(userId: number): Observable<SavedRecipeResponse[]> {
    return this.http
      .get<SavedRecipeResponse[]>(`${this.API_URL}${this.ENDPOINT}/user/${userId}`)
      .pipe(
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
  addFavorite(recipeId: number): Observable<SavedRecipeResponse> {
    return this.http
      .post<SavedRecipeResponse>(`${this.API_URL}${this.ENDPOINT}/${recipeId}`, {})
      .pipe(
        tap((response) => {
          this.favoriteRecipeIdsWritable.update((currentIds) => {
            console.log('user-save-service', response.recipeId);
            console.log('user-save-service', response);
            const newIds = new Set(currentIds);
            newIds.add(response.recipeId);
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
