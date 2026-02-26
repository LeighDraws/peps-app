export enum AuthProvider {
  LOCAL = 'Local',
  GOOGLE = 'Google',
  MICROSOFT = 'Microsoft',
}

export interface User {
  id: number;
  pseudo: string;
  email: string;
  password?: string;
  avatarUrl?: string;
  authProvider: AuthProvider;
  providerId?: string;
  createdAt: string;
  updatedAt: string;
}
