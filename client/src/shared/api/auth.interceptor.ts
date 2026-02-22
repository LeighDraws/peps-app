import {
  HttpErrorResponse,
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, filter, switchMap, take, throwError } from 'rxjs';
import { AuthService } from '../../entities/User/service/auth.service';

let isRefreshing = false;
const refreshTokenSubject = new BehaviorSubject<boolean | null>(null);

export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn,
) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const authReq = req.clone({
    withCredentials: true,
  });

  return next(authReq).pipe(
    catchError((error) => {
      if (
        error instanceof HttpErrorResponse &&
        error.status === 401 &&
        !req.url.includes('/login') &&
        !req.url.includes('/refresh')
      ) {
        // 1. Si on n'est PAS déjà en train de rafraîchir
        if (!isRefreshing) {
          isRefreshing = true;
          refreshTokenSubject.next(null); // On bloque les autres

          return authService.refreshToken().pipe(
            switchMap(() => {
              isRefreshing = false;
              refreshTokenSubject.next(true); // On débloque les autres
              return next(authReq); // On rejoue la requête initiale
            }),
            catchError((refreshError) => {
              isRefreshing = false;
              // Si le refresh échoue (ex: refresh token expiré), on déconnecte
              return authService.logout().pipe(
                switchMap(() => {
                  router.navigate(['/login']);
                  return throwError(() => refreshError);
                }),
              );
            }),
          );
        } else {
          // 2. Si on est DÉJÀ en train de rafraîchir, on met la requête en file d'attente
          return refreshTokenSubject.pipe(
            filter((result) => result !== null), // On attend que le subject reçoive une valeur (le feu vert)
            take(1), // On ne prend que la première émission
            switchMap(() => {
              return next(authReq); // On rejoue la requête initiale
            }),
          );
        }
      }

      // Si ce n'est pas une 401 ou si c'est sur /login, on renvoie l'erreur normalement
      return throwError(() => error);
    }),
  );
};
