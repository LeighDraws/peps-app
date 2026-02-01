import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/users';

  private http = inject(HttpClient);

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createUser(user: Omit<User, 'id'>): Observable<User> {
    return this.http.post<User>(`${this.API_URL}${this.ENDPOINT}`, user);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.API_URL}${this.ENDPOINT}/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
