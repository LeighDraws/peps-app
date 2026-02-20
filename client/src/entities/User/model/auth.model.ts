import { User } from './user';

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  pseudo: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: User;
}
