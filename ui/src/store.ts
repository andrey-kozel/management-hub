/*
 * Copyright (C) 2022 Spanning Cloud Apps.  All rights reserved.
 */

import create from 'zustand';
import { SessionState } from './model/SessionState';
import SessionService from './service/SessionService';

const initialState: SessionState = {
  user: null,
  loading: true
};

export const userSessionStore = create<SessionState>(set => ({
  ...initialState,
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
