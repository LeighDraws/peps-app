export enum AuthProvider {
    LOCAL = 'LOCAL',
    GOOGLE = 'GOOGLE',
    MICROSOFT = 'MICROSOFT'
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
