import { Component, Input } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faHeart, faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { Recipe } from 'src/entities/Recipe/model/recipe';

@Component({
  selector: 'app-recipe-card',
  imports: [FontAwesomeModule],
  templateUrl: './recipe-card.html',
  styleUrl: './recipe-card.css',
})
export class RecipeCard {
  faHeart = faHeart;
  faPlus = faPlusCircle;
  @Input() recipe!: Recipe;
}
