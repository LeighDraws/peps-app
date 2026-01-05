export interface IStep {
  id: number;
  stepNumber: number;
  instruction: string;
  imageUrl?: string;
  recipeId: number;
}
