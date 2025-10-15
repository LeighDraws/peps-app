import { Component } from '@angular/core';
import { RecipeListComponent } from 'src/features/recipes-list/recipe-list-component';

@Component({
  selector: 'app-home-page',
  imports: [RecipeListComponent],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {}
