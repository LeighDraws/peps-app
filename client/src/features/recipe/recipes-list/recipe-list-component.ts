import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, Signal } from '@angular/core';
import { Recipe } from 'src/entities/Recipe/model/recipe';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';
import { RecipeCard } from '../recipe-card/recipe-card';
import { RecipeFilter } from '../recipe-filter/recipe-filter';

@Component({
  selector: 'app-recipe-list-component',
  standalone: true,
  imports: [CommonModule, RecipeCard, RecipeFilter],
  templateUrl: './recipe-list-component.html',
  styleUrl: './recipe-list-component.css',
})
export class RecipeListComponent implements OnInit {
  // Signal Recipes pour stocker les recettes
  recipes!: Signal<Recipe[]>;

  // Injection du service RecipeService
  private recipeService: RecipeService = inject(RecipeService);

  // A l'initialisation du composant, on récupère toutes les recettes depuis le service
  ngOnInit(): void {
    this.recipes = this.recipeService.recipes;
    this.recipeService.loadRecipes();
  }
}
