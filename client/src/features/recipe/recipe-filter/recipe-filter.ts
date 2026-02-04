import { Component } from '@angular/core';
import { ButtonFilterComponent } from 'src/shared/components/button-filter/button-filter';
import { faCarrot, faEarthAmerica, faUtensils } from '@fortawesome/free-solid-svg-icons';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-recipe-filter',
  standalone: true,
  imports: [ButtonFilterComponent, FontAwesomeModule],
  templateUrl: './recipe-filter.html',
  styleUrl: './recipe-filter.css',
})
export class RecipeFilter {
  carrotIcon: IconDefinition = faCarrot;
  earthIcon: IconDefinition = faEarthAmerica;
  utensilsIcon: IconDefinition = faUtensils;
}
