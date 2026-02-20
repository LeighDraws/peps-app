import { Component, inject, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faHome,
  faBook,
  faCalendarDays,
  faUser,
  faPowerOff,
  faArrowRightToBracket,
} from '@fortawesome/free-solid-svg-icons';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-sidenav',
  imports: [FontAwesomeModule, RouterLink, RouterLinkActive],
  templateUrl: './sidenav.html',
  styleUrl: './sidenav.css',
})
export class Sidenav implements OnInit {
  faHome = faHome;
  faBook = faBook;
  faCalendarDays = faCalendarDays;
  faUser = faUser;
  faPowerOff = faPowerOff;
  faArrowRightToBracket = faArrowRightToBracket;

  protected authService = inject(AuthService);
  private readonly router = inject(Router);

  ngOnInit(): void {
    console.log(this.authService.isLoaded());
  }

  logout() {
    this.authService.logout().subscribe({
      next: () => {
        // Force le rechargement des informations de l'utilisateur pour mettre à jour l'état
        this.authService.getCurrentUser().subscribe(() => {
          this.router.navigate(['/home']);
        });
      },
      error: (err) => {
        console.error('Logout failed', err);
        // Même en cas d'erreur, on tente de rafraîchir et de rediriger
        this.authService.getCurrentUser().subscribe(() => {
          this.router.navigate(['/home']);
        });
      },
    });
  }
}
