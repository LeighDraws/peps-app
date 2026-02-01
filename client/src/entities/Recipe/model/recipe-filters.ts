import { Category } from './recipe';

export interface RecipeFilters {
  countryId?: number;
  category?: Category;
  includedIngredientIds?: number[];
  excludedIngredientIds?: number[];
}
