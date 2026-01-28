import { User } from '../../User/model/user';
import { Recipe } from '../../Recipe/model/recipe';

export interface UserSaveRecipe {
  id: number;
  user: User;
  recipe: Recipe;
  createdAt: string;
  updatedAt: string;
}
