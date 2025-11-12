import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Recipe } from 'src/entities/Recipe/model/recipe';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-recipe-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSnackBarModule,
  ],
  templateUrl: './recipe-form.html',
  styleUrl: './recipe-form.css',
})
export class RecipeForm {
  private fb: FormBuilder = inject(FormBuilder);
  private recipeService: RecipeService = inject(RecipeService);
  private snackBar: MatSnackBar = inject(MatSnackBar);

  recipeForm = this.fb.group({
    name: [''],
    description: [''],
    durationMinutes: [null],
    ingredients: [''],
  });

  onSubmit() {
    if (this.recipeForm.valid) {
      const formValue = this.recipeForm.value;
      const recipe = {
        ...formValue,
        ingredients: formValue.ingredients
          ? formValue.ingredients.split(',').map((i) => i.trim())
          : [],
      } as Recipe;

      console.log('Submitting recipe:', recipe);

      this.recipeService.createRecipe(recipe).subscribe(() => {
        this.snackBar.open('Recette ajoutée avec succès 🎉', 'Fermer', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
        });

        this.recipeForm.reset();
      });
    }
  }
}
