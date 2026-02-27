import { Component, computed, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Recipe } from 'src/entities/Recipe/model/recipe';
import { UserSaveRecipeService } from 'src/entities/UserSaveRecipe/service/user-save-recipe.service';
import { RecipeCard } from 'src/features/recipe/recipe-card/recipe-card';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';

@Component({
  selector: 'app-my-recipes-page',
  standalone: true,
  imports: [CommonModule, RouterLink, RecipeCard],
  templateUrl: './my-recipes.page.html',
  styleUrls: ['./my-recipes.page.css'],
})
export class MyRecipesPageComponent {
  private readonly recipeService = inject(RecipeService);
  public readonly userSaveRecipeService = inject(UserSaveRecipeService);

  public errorMessage = signal<string | null>(null);

  private allRecipes = this.recipeService.recipes;
  private favoriteRecipeIds = this.userSaveRecipeService.favoriteRecipeIds;

  public favoriteRecipes = computed(() => {
    const all = this.allRecipes();
    const favIds = this.favoriteRecipeIds();
    return all.filter((recipe) => favIds.has(recipe.id));
  });

  constructor() {
    this.recipeService.loadRecipes();
  }

  onFavoriteClick(recipe: Recipe): void {
    this.errorMessage.set(null);

    const isFavorite = this.favoriteRecipeIds().has(recipe.id);

    const operation = isFavorite
      ? this.userSaveRecipeService.removeFavorite(recipe.id)
      : this.userSaveRecipeService.addFavorite(recipe.id);

    operation.subscribe({
      error: (err) => {
        console.error('Failed to update favorite status:', err);
        this.errorMessage.set('Erreur lors de la mise à jour de vos favoris.');
      },
    });
  }

  isRecipeFavorite(recipeId: number): boolean {
    return this.favoriteRecipeIds().has(recipeId);
  }
}
