import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-button-filter',
  imports: [CommonModule, FontAwesomeModule],
  templateUrl: './button-filter.html',
  styleUrl: './button-filter.css',
  standalone: true,
})
export class ButtonFilterComponent {
  @Input() label = '';
  @Input() isActive = false;
  @Input() icon: IconDefinition | undefined;
}
