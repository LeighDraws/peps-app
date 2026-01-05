import { inject, Injectable, signal } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, of, tap } from 'rxjs';
import { IStep } from '../model/step';
import { IStepService } from './Istep.service';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class StepApiService implements IStepService {
  private apiUrl = `${environment.apiUrl}/steps`;
  steps = signal<IStep[]>([]);
  private http = inject(HttpClient);

  getAllSteps(): Observable<IStep[]> {
    return this.http.get<IStep[]>(this.apiUrl).pipe(
      tap((steps) => this.steps.set(steps)),
      catchError(this.handleError<IStep[]>('getAllSteps', [])),
    );
  }

  getStepById(id: number): Observable<IStep> {
    const url = `${this.apiUrl}/${id}`;
    return this.http
      .get<IStep>(url)
      .pipe(catchError(this.handleError<IStep>(`getStepById id=${id}`)));
  }

  createStep(step: IStep): Observable<IStep> {
    return this.http.post<IStep>(this.apiUrl, step).pipe(
      tap((newStep) => this.steps.update((steps) => [...steps, newStep])),
      catchError(this.handleError<IStep>('createStep')),
    );
  }

  updateStep(id: number, step: IStep): Observable<IStep> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<IStep>(url, step).pipe(
      tap((updatedStep) =>
        this.steps.update((steps) => steps.map((s) => (s.id === updatedStep.id ? updatedStep : s))),
      ),
      catchError(this.handleError<IStep>('updateStep')),
    );
  }

  deleteStep(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url).pipe(
      tap(() => this.steps.update((steps) => steps.filter((s) => s.id !== id))),
      catchError(this.handleError<void>('deleteStep')),
    );
  }

  getStepsByRecipeId(recipeId: number): Observable<IStep[]> {
    const url = `${this.apiUrl}/by-recipe/${recipeId}`;
    return this.http
      .get<IStep[]>(url)
      .pipe(catchError(this.handleError<IStep[]>(`getStepsByRecipeId recipeId=${recipeId}`, [])));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: HttpErrorResponse): Observable<T> => {
      console.error(`${operation} failed:`, error);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
