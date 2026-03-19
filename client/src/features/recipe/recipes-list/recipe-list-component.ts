import { CommonModule } from '@angular/common';
import { Component, computed, inject, input, signal } from '@angular/core';
import { Recipe } from 'src/entities/Recipe/model/recipe';
import { RecipeCard } from '../recipe-card/recipe-card';
import { RecipeFilter } from '../recipe-filter/recipe-filter';
import { UserSaveRecipeService } from 'src/entities/UserSaveRecipe/service/user-save-recipe.service';
import { SearchService } from 'src/shared/services/search.service';

@Component({
  selector: 'app-recipe-list-component',
  standalone: true,
  imports: [CommonModule, RecipeCard, RecipeFilter],
  templateUrl: './recipe-list-component.html',
  styleUrl: './recipe-list-component.css',
})
export class RecipeListComponent {
  recipes = input<Recipe[]>([]);

  private userSaveRecipeService = inject(UserSaveRecipeService);
  private searchService = inject(SearchService);
  public errorMessage = signal<string | null>(null);

  filteredRecipes = computed(() => {
    const q = this.searchService.searchQuery().toLowerCase();
    return this.recipes().filter((r) => r.name.toLowerCase().includes(q));
  });

  isRecipeFavorite(recipeId: number): boolean {
    return this.userSaveRecipeService.favoriteRecipeIds().has(recipeId);
  }

  onFavoriteClick(recipe: Recipe): void {
    this.errorMessage.set(null);
    const isFavorite = this.isRecipeFavorite(recipe.id);

    const errorHandler = {
      error: (err: Error) => {
        console.error('Failed to update favorite status:', err);
        this.errorMessage.set('Erreur lors de la mise à jour de vos favoris.');
      },
    };

    if (isFavorite) {
      this.userSaveRecipeService.removeFavorite(recipe.id).subscribe(errorHandler);
    } else {
      this.userSaveRecipeService.addFavorite(recipe.id).subscribe(errorHandler);
    }
  }
}
