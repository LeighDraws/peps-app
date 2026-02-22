import { Component, inject } from '@angular/core';
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
export class Sidenav {
  faHome = faHome;
  faBook = faBook;
  faCalendarDays = faCalendarDays;
  faUser = faUser;
  faPowerOff = faPowerOff;
  faArrowRightToBracket = faArrowRightToBracket;

  protected authService = inject(AuthService);
  private readonly router = inject(Router);

  logout() {
    this.authService.logout().subscribe({
      next: () => {
        // Le signal a déjà été mis à null par le service
        console.log('Déconnexion réussie');
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Logout failed', err);
        this.router.navigate(['/home']);
      },
    });
  }
}
