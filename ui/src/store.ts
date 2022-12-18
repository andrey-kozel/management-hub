/*
 * Copyright (C) 2022 Spanning Cloud Apps.  All rights reserved.
 */

import create from 'zustand';
import { User } from './model/SessionState';
import SessionService from './service/SessionService';

export interface SessionState {
  user: User | null;
  loading: boolean;
  getSession: () => void;
}

export const useSessionStore = create<SessionState>(set => ({
  user: null,
  loading: true,
  getSession: async () => {
    try {
      const user = await SessionService.getSession();
      set({ user });
    } catch (error) {
      set({ user: null });
    } finally {
      set({ loading: false });
    }
  }
}));
