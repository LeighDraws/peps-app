import { Component } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faHeart, faPlusCircle } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-recipe-card',
  imports: [FontAwesomeModule],
  templateUrl: './recipe-card.html',
  styleUrl: './recipe-card.css',
})
export class RecipeCard {
  faHeart = faHeart;
  faPlus = faPlusCircle;
}
