import { Component, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { UserSaveRecipeService } from 'src/entities/UserSaveRecipe/service/user-save-recipe.service';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';
import { RecipeListComponent } from 'src/features/recipe/recipes-list/recipe-list-component';

@Component({
  selector: 'app-my-recipes-page',
  standalone: true,
  imports: [CommonModule, RouterLink, RecipeListComponent],
  templateUrl: './my-recipes.page.html',
  styleUrls: ['./my-recipes.page.css'],
})
export class MyRecipesPageComponent {
  private readonly recipeService = inject(RecipeService);
  public readonly userSaveRecipeService = inject(UserSaveRecipeService);

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
}
