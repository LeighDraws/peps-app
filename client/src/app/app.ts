import { Component, inject, OnInit } from '@angular/core';
import { AppLayout } from './layout/app-layout';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-root',
  imports: [AppLayout],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  private authService = inject(AuthService);

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe({
      next: (user) => {
        console.log('User loaded:', user);
      },
      error: (err) => {
        console.error('Error loading user:', err);
      },
    });
  }
}
