import { Recipe } from '../../Recipe/model/recipe';
import { Ingredient } from '../../Ingredient/model/ingredient';

export enum MeasurementUnit {
    MILLIGRAM = 'MILLIGRAM',
    GRAM = 'GRAM',
    KILOGRAM = 'KILOGRAM',
    LITER = 'LITER',
    CENTILITER = 'CENTILITER',
    MILLILITER = 'MILLILITER',
    TEASPOON = 'TEASPOON',
    TABLESPOON = 'TABLESPOON',
    CUP = 'CUP',
    PIECE = 'PIECE',
    SLICE = 'SLICE',
    PINCH = 'PINCH'
}

export interface RecipeHasIngredient {
  id: number;
  recipe: Recipe;
  ingredient: Ingredient;
  quantity: number;
  measurementUnit: MeasurementUnit;
  createdAt: string;
  updatedAt: string;
}
