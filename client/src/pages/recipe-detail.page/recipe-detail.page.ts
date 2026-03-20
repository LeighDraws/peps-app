import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { forkJoin, of } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';

import { RecipeService } from 'src/entities/Recipe/service/recipe-service';
import { RecipeHasIngredientService } from 'src/entities/RecipeHasIngredient/service/recipe-has-ingredient.service';
import { StepApiService } from 'src/entities/Step/service/step-api.service';
import { RecipeHasTagService } from 'src/entities/RecipeHasTag/service/recipe-has-tag.service';

import { Recipe } from 'src/entities/Recipe/model/recipe';
import { RecipeHasIngredientResponse } from 'src/entities/RecipeHasIngredient/model/recipe-has-ingredient';
import { StepResponse } from 'src/entities/Step/model/step';
import { RecipeHasTagResponse } from 'src/entities/RecipeHasTag/model/recipe-has-tag';

import { RecipeDetailComponent } from 'src/features/recipe/recipe-detail/recipe-detail.component';
import { BackButtonComponent } from 'src/shared/components/back-button/back-button.component';

@Component({
  selector: 'app-recipe-detail-page',
  standalone: true,
  imports: [CommonModule, RecipeDetailComponent, BackButtonComponent],
  templateUrl: './recipe-detail.page.html',
  styleUrls: ['./recipe-detail.page.css'],
})
export class RecipeDetailPageComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private recipeService = inject(RecipeService);
  private ingredientService = inject(RecipeHasIngredientService);
  private stepService = inject(StepApiService);
  private tagService = inject(RecipeHasTagService);

  recipe = signal<Recipe | null>(null);
  ingredients = signal<RecipeHasIngredientResponse[]>([]);
  steps = signal<StepResponse[]>([]);
  tags = signal<RecipeHasTagResponse[]>([]);

  isLoading = signal<boolean>(true);
  errorMessage = signal<string | null>(null);

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadRecipeData(id);
    } else {
      this.errorMessage.set('ID de recette manquant.');
      this.isLoading.set(false);
    }
  }

  private loadRecipeData(id: string): void {
    this.isLoading.set(true);
    const recipeId = parseInt(id, 10);

    forkJoin({
      recipe: this.recipeService.getRecipeById(id),
      ingredients: this.ingredientService
        .getIngredientsByRecipeId(recipeId)
        .pipe(catchError(() => of([]))),
      steps: this.stepService.getStepsByRecipeId(recipeId).pipe(catchError(() => of([]))),
      tags: this.tagService.getTagsByRecipeId(recipeId).pipe(catchError(() => of([]))),
    })
      .pipe(
        finalize(() => this.isLoading.set(false)),
        catchError((error) => {
          console.error('Error fetching recipe detail:', error);
          this.errorMessage.set('Une erreur est survenue lors du chargement de la recette.');
          return of(null);
        }),
      )
      .subscribe((data) => {
        if (data) {
          this.recipe.set(data.recipe);
          this.ingredients.set(data.ingredients);
          this.steps.set(data.steps);
          this.tags.set(data.tags);
        }
      });
  }
}
