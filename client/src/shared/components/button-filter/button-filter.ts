import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-button-filter',
  imports: [CommonModule],
  templateUrl: './button-filter.html',
  styleUrl: './button-filter.css',
  standalone: true,
})
export class ButtonFilterComponent {
  @Input() label = '';
  @Input() isActive = false;
}
