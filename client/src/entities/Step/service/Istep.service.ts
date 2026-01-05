import { Observable } from 'rxjs';
import { IStep } from '../model/step';

export interface IStepService {
  getAllSteps(): Observable<IStep[]>;
  getStepById(id: number): Observable<IStep>;
  createStep(step: IStep): Observable<IStep>;
  updateStep(id: number, step: IStep): Observable<IStep>;
  deleteStep(id: number): Observable<void>;
  getStepsByRecipeId(recipeId: number): Observable<IStep[]>;
}
