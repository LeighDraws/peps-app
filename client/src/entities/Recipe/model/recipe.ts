import { Country } from 'src/entities/Country/model/country';
import { IStep } from '../../Step/model/step';
import { User } from 'src/entities/User/model/user';

export enum Category {
    VEGAN = 'Vegan',
    VEGETARIAN = 'Vegetarian',
    MEAT = 'Meat',
    SEAFOOD = 'Seafood',
    GLUTENFREE = 'Gluten Free',
    DESSERT = 'Dessert',
    BEVERAGE = 'Beverage',
    SNACK = 'Snack',
    OTHER = 'Other'
}

export enum Difficulty {
    EASY = 'Easy',
    NORMAL = 'Normal',
    HARD = 'Hard'
}

export enum PriceRange {
    VERY_CHEAP = 'Very Cheap',
    CHEAP = 'Cheap',
    NORMAL = 'Normal',
    EXPENSIVE = 'Expensive',
    VERY_EXPENSIVE = 'Very Expensive'
}


export interface Recipe {
  id: number;
  name: string;
  description: string;
  imageUrl?: string;
  preparationDuration?: number;
  category?: Category;
  priceRange?: PriceRange;
  difficulty?: Difficulty;
  createdAt: string; // Assuming ISO 8601 format string
  updatedAt: string; // Assuming ISO 8601 format string
  country?: Country;
  user?: User;
  steps?: IStep[];
}
