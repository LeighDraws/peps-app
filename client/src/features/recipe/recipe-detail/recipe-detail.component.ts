import { Component, input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Recipe, Difficulty, PriceRange } from 'src/entities/Recipe/model/recipe';
import { RecipeHasIngredientResponse } from 'src/entities/RecipeHasIngredient/model/recipe-has-ingredient';
import { StepResponse } from 'src/entities/Step/model/step';
// import { PriceRange } from 'src/entities/Recipe/model/recipe';
import { RecipeHasTagResponse } from 'src/entities/RecipeHasTag/model/recipe-has-tag';
import { Category } from 'src/entities/Recipe/model/recipe-filters';

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

  // Expose l'enum pour le template
  Difficulty = Difficulty;

  get difficultyClass(): string {
    const difficulty = this.recipe().difficulty as unknown as string;
    switch (difficulty) {
      case 'EASY':
        return 'badge-success';
      case 'NORMAL':
        return 'badge-warning';
      case 'HARD':
        return 'badge-error';
      default:
        return 'badge-ghost';
    }
  }

  get difficultyLabel(): string {
    const difficulty = this.recipe().difficulty;
    if (!difficulty) return 'Non spécifiée';
    return (Difficulty as Record<string, string>)[difficulty] || difficulty;
  }

  get priceLabel(): string {
    const price = this.recipe().priceRange;
    if (!price) return 'Non spécifié';
    return (PriceRange as Record<string, string>)[price] || price;
  }

  get categoryLabel(): string {
    const category = this.recipe().category;
    if (!category) return 'Pas de catégorie';
    return (Category as Record<string, string>)[category] || category;
  }
}
