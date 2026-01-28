import { User } from '../../User/model/user';

export interface Menu {
  id: number;
  name: string;
  date?: string;
  user: User;
  createdAt: string;
  updatedAt: string;
}
