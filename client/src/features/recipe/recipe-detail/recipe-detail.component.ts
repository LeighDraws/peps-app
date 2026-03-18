import { Component, input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Recipe } from 'src/entities/Recipe/model/recipe';
import { RecipeHasIngredientResponse } from 'src/entities/RecipeHasIngredient/model/recipe-has-ingredient';
import { StepResponse } from 'src/entities/Step/model/step';
import { RecipeHasTagResponse } from 'src/entities/RecipeHasTag/model/recipe-has-tag';

@Component({
  selector: 'app-recipe-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css'],
})
export class RecipeDetailComponent {
  recipe = input.required<Recipe>();
  ingredients = input<RecipeHasIngredientResponse[]>([]);
  steps = input<StepResponse[]>([]);
  tags = input<RecipeHasTagResponse[]>([]);

  get difficultyClass(): string {
    const difficulty = this.recipe().difficulty;
    switch (difficulty) {
      case 'Facile':
        return 'badge-success';
      case 'Moyen':
        return 'badge-warning';
      case 'Difficile':
        return 'badge-error';
      default:
        return 'badge-ghost';
    }
  }

  get priceClass(): string {
    const price = this.recipe().priceRange;
    if (price === 'Very Cheap' || price === 'Cheap') return 'badge-success';
    if (price === 'Normal') return 'badge-info';
    return 'badge-warning';
  }
}
