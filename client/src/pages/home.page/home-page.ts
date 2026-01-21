import { Component } from '@angular/core';
import { RecipeListComponent } from 'src/features/recipe/recipes-list/recipe-list-component';
import { RecipeForm } from "src/features/recipe/recipe-form/recipe-form";

@Component({
  selector: 'app-home-page',
  imports: [RecipeListComponent, RecipeForm],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {}
