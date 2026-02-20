import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../entities/User/service/auth.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  email = signal('');
  password = signal('');
  errorMessage = signal<string | null>(null);
  isLoading = signal(false);

  onSubmit() {
    if (!this.email() || !this.password()) {
      this.errorMessage.set('Veuillez remplir tous les champs.');
      return;
    }

    this.isLoading.set(true);
    this.errorMessage.set(null);

    const credentials = {
      email: this.email(),
      password: this.password(),
    };

    this.authService.login(credentials).subscribe({
      next: () => {
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.isLoading.set(false);
        console.error('Login failed', error);

        if (error.status === 400) {
          this.errorMessage.set('Le format des données est incorrect.');
        } else if (error.status === 401) {
          this.errorMessage.set('Email ou mot de passe incorrect.');
        } else {
          this.errorMessage.set(
            'Une erreur est survenue lors de la connexion. Veuillez réessayer plus tard.',
          );
        }
      },
    });
  }

  onInputChange() {
    if (this.errorMessage()) {
      this.errorMessage.set(null);
    }
  }
}
