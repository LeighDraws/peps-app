import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class HealthService {
  public API_URL: string = environment.apiUrl;
  public apiState = signal('client');

  private http = inject(HttpClient);

  tryConnexion() {
    this.http.get<{ status: string; message: string }>(`${this.API_URL}/api/health`).subscribe({
      next: (data) => {
        this.apiState.set(data.message);
        console.log('API is healthy:', data);
      },
      error: (error) => {
        this.apiState.set('API is not tapis tapis gris :(');
        console.error('API is not reachable:', error);
      },
    });
  }
}
