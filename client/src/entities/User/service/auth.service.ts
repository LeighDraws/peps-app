import { computed, inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, finalize, Observable, tap, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { LoginRequest, RegisterRequest } from '../model/auth.model';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/auth`;

  private isLoadedSignal = signal<boolean>(false);
  public isLoaded = this.isLoadedSignal.asReadonly();

  private currentUserSignal = signal<User | null>(null);
  public currentUser = this.currentUserSignal.asReadonly();
  // Signal qui renvoie TRUE si l'utilisateur est connecté
  public isAuthenticated = computed(() => !!this.currentUserSignal());

  login(credentials: LoginRequest): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/login`, credentials).pipe(
      tap((response) => {
        this.currentUserSignal.set(response);
      }),
    );
  }

  register(userData: RegisterRequest): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/register`, userData).pipe(
      tap((response) => {
        this.currentUserSignal.set(response);
      }),
    );
  }

  logout(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/logout`, {}).pipe(
      tap(() => {
        this.currentUserSignal.set(null);
      }),
    );
  }

  refreshToken(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/refresh`, {});
  }

  /**
   * Récupère les informations de l'utilisateur connecté via le cookie
   */
  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}/auth/me`).pipe(
      tap((user) => {
        // On met à jour le signal avec les données reçues
        this.currentUserSignal.set(user);
      }),
      catchError((err) => {
        // Si erreur (401), on s'assure que l'utilisateur est bien à null
        this.currentUserSignal.set(null);
        return throwError(() => err);
      }),
      finalize(() => {
        this.isLoadedSignal.set(true);
        console.log('AuthService: getCurrentUser completed, isLoaded set to true');
      }),
    );
  }
}
