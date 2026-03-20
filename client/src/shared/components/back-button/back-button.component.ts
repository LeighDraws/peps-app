import { Component, inject } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-back-button',
  standalone: true,
  imports: [CommonModule, FontAwesomeModule],
  template: `
    <button (click)="goBack()" class="btn btn-primary">
      <fa-icon [icon]="faArrowLeft"></fa-icon>
      <span class="font-bold text-sm">Retour</span>
    </button>
  `,
})
export class BackButtonComponent {
  faArrowLeft = faArrowLeft;

  private location = inject(Location);

  goBack(): void {
    this.location.back();
  }
}
