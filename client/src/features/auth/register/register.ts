import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  pseudo = signal('');
  email = signal('');
  password = signal('');

  errorMessage = signal<string | null>(null);
  isLoading = signal(false);

  onSubmit() {
    if (!this.pseudo() || !this.email() || !this.password()) {
      this.errorMessage.set('Veuillez remplir tous les champs.');
      return;
    }

    this.isLoading.set(true);
    this.errorMessage.set(null);

    const userData = {
      pseudo: this.pseudo(),
      email: this.email(),
      password: this.password(),
    };

    this.authService.register(userData).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: (error) => {
        this.isLoading.set(false);
        console.error('Registration failed', error);

        if (error.status === 400) {
          this.errorMessage.set('Le format des données est incorrect.');
        } else if (error.status === 409) {
          this.errorMessage.set('Cet email est déjà utilisé.');
        } else {
          this.errorMessage.set(
            "Une erreur est survenue lors de l'inscription. Veuillez réessayer plus tard.",
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
