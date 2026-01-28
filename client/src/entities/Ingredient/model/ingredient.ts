export enum IngredientCategory {
  MEAT = 'MEAT',
  FISH = 'FISH',
  VEGETABLE = 'VEGETABLE',
  FRUIT = 'FRUIT',
  CEREAL = 'CEREAL',
  DAIRY = 'DAIRY',
  SPICE = 'SPICE',
  OTHER = 'OTHER',
}

export interface Ingredient {
  id: number;
  name: string;
  category: IngredientCategory;
  createdAt: string;
  updatedAt: string;
}
