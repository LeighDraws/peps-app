import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MenuHasRecipe } from '../model/menu-has-recipe';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MenuHasRecipeService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/menu-has-recipes';

  private http = inject(HttpClient);

  getAllMenuHasRecipes(): Observable<MenuHasRecipe[]> {
    return this.http.get<MenuHasRecipe[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getMenuHasRecipeById(id: number): Observable<MenuHasRecipe> {
    return this.http.get<MenuHasRecipe>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createMenuHasRecipe(menuHasRecipe: Omit<MenuHasRecipe, 'id'>): Observable<MenuHasRecipe> {
    return this.http.post<MenuHasRecipe>(`${this.API_URL}${this.ENDPOINT}`, menuHasRecipe);
  }

  updateMenuHasRecipe(id: number, menuHasRecipe: MenuHasRecipe): Observable<MenuHasRecipe> {
    return this.http.put<MenuHasRecipe>(`${this.API_URL}${this.ENDPOINT}/${id}`, menuHasRecipe);
  }

  deleteMenuHasRecipe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
