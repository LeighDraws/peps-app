import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-profile-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile-page.html',
  styleUrl: './profile-page.css',
})
export class ProfilePage {
  protected readonly authService = inject(AuthService);
  private readonly router = inject(Router);
  protected readonly user = this.authService.currentUser;

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
