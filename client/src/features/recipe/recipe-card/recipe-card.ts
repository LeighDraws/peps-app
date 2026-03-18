import { Component, Input, Output, EventEmitter, inject } from '@angular/core';
import { NgClass, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faHeart, faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { faHeart as faHeartRegular } from '@fortawesome/free-regular-svg-icons';
import { Recipe } from 'src/entities/Recipe/model/recipe';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [NgIf, NgClass, FontAwesomeModule, RouterLink],
  templateUrl: './recipe-card.html',
  styleUrls: ['./recipe-card.css'],
})
export class RecipeCard {
  faHeart = faHeart;
  faPlus = faPlusCircle;
  faHeartRegular = faHeartRegular;

  @Input() recipe!: Recipe;
  @Input() isFavorite = false;
  @Output() favoriteClick = new EventEmitter<void>();

  private authService = inject(AuthService);
  public isAuthenticated = this.authService.isAuthenticated;

  get recipeUser(): string {
    return this.recipe.user?.pseudo || 'Unknown';
  }

  toggleFavorite(): void {
    if (!this.isAuthenticated()) {
      alert('You need to be logged in to favorite recipes.');
      return;
    }
    this.favoriteClick.emit();
    console.log('RecipeCard: favoriteClick emitted for recipe ID', this.recipe.id);
  }
}
