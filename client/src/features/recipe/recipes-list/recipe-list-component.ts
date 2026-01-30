import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { catchError, map, Observable } from 'rxjs';
import { Recipe } from 'src/entities/Recipe/model/recipe';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';
import { RecipeCard } from '../recipe-card/recipe-card';
import { ModalComponent } from 'src/shared/components/modal/modal';
import { ButtonFilterComponent } from 'src/shared/components/button-filter/button-filter';

@Component({
  selector: 'app-recipe-list-component',
  imports: [CommonModule, RecipeCard, ModalComponent, ButtonFilterComponent],
  templateUrl: './recipe-list-component.html',
  styleUrl: './recipe-list-component.css',
})
export class RecipeListComponent implements OnInit {
  // Observable Recipes pour stocker les recettes
  recipes$!: Observable<Recipe[]>;

  // Injection du service RecipeService
  private recipeService: RecipeService = inject(RecipeService);

  // A l'initialisation du composant, on récupère toutes les recettes depuis le service
  ngOnInit(): void {
    this.recipes$ = this.recipeService.getAllRecipes().pipe(
      map((recipes) => recipes || []),
      catchError((err) => {
        console.error('Error fetching recipes', err);
        throw err;
      }),
    );
    console.log(this.recipes$.subscribe((data) => console.log(data)));
  }
}
