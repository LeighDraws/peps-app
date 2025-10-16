export interface Recipe {
  id: number;
  name: string;
  description: string;
  durationMinutes?: number;
  ingredients?: string[]; // Faire une interface Ingredient plus tard
}
