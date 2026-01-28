import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../model/menu';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/menus';

  private http = inject(HttpClient);

  getAllMenus(): Observable<Menu[]> {
    return this.http.get<Menu[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getMenuById(id: number): Observable<Menu> {
    return this.http.get<Menu>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createMenu(menu: Omit<Menu, 'id'>): Observable<Menu> {
    return this.http.post<Menu>(`${this.API_URL}${this.ENDPOINT}`, menu);
  }

  updateMenu(id: number, menu: Menu): Observable<Menu> {
    return this.http.put<Menu>(`${this.API_URL}${this.ENDPOINT}/${id}`, menu);
  }

  deleteMenu(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
