import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserSaveRecipe } from '../model/user-save-recipe';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserSaveRecipeService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/user-save-recipes';

  private http = inject(HttpClient);

  getAllUserSaveRecipes(): Observable<UserSaveRecipe[]> {
    return this.http.get<UserSaveRecipe[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getUserSaveRecipeById(id: number): Observable<UserSaveRecipe> {
    return this.http.get<UserSaveRecipe>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createUserSaveRecipe(userSaveRecipe: Omit<UserSaveRecipe, 'id'>): Observable<UserSaveRecipe> {
    return this.http.post<UserSaveRecipe>(`${this.API_URL}${this.ENDPOINT}`, userSaveRecipe);
  }

  updateUserSaveRecipe(id: number, userSaveRecipe: UserSaveRecipe): Observable<UserSaveRecipe> {
    return this.http.put<UserSaveRecipe>(`${this.API_URL}${this.ENDPOINT}/${id}`, userSaveRecipe);
  }

  deleteUserSaveRecipe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
