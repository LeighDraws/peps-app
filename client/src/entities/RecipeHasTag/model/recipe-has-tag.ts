import { Recipe } from '../../Recipe/model/recipe';
import { Tag } from '../../Tag/model/tag';

export interface RecipeHasTag {
  id: number;
  recipe: Recipe;
  tag: Tag;
  createdAt: string;
  updatedAt: string;
}

export interface RecipeHasTagResponse {
  id: number;
  recipeId: number;
  tagId: number;
  tagName: string;
  createdAt: string;
  updatedAt: string;
}
