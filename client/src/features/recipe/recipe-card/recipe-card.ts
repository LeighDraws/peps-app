import { NgIf } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faHeart, faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { Recipe } from 'src/entities/Recipe/model/recipe';

@Component({
  selector: 'app-recipe-card',
  imports: [FontAwesomeModule, NgIf],
  templateUrl: './recipe-card.html',
  styleUrl: './recipe-card.css',
})
export class RecipeCard implements OnInit {
  faHeart = faHeart;
  faPlus = faPlusCircle;
  @Input() recipe!: Recipe;
  recipeUser = '';

  ngOnInit(): void {
    this.recipeUser = this.recipe.user?.pseudo || 'Unknown';
  }
}
