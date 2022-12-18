export interface User {
  accountId: string;
  username: string;
}

export interface SessionState {
  user: User | null;
  loading: boolean;
}
