import { Component, inject } from '@angular/core';
import { RecipeListComponent } from 'src/features/recipe/recipes-list/recipe-list-component';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RecipeListComponent],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {
  private recipeService = inject(RecipeService);
  public recipes = this.recipeService.recipes;

  constructor() {
    this.recipeService.loadRecipes();
  }
}
