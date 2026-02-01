import { Menu } from '../../Menu/model/menu';
import { Recipe } from '../../Recipe/model/recipe';

export interface MenuHasRecipe {
  id: number;
  menu: Menu;
  recipe: Recipe;
  createdAt: string;
  updatedAt: string;
}
