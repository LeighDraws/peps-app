export enum Category {
  VEGAN = 'Vegan',
  VEGETARIAN = 'Végétarien',
  MEAT = 'Avec Viande',
  NO_FISH = 'Sans Poisson',
  GLUTENFREE = 'Sans Gluten',
  DESSERT = 'Dessert',
  BEVERAGE = 'Boisson',
  SNACK = 'Snack',
  OTHER = 'Autre',
}

export interface RecipeFilters {
  countryId?: number;
  category?: Category;
  includedIngredientIds?: number[];
  excludedIngredientIds?: number[];
}
