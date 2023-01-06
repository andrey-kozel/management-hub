/*
 * Copyright (C) 2022 Spanning Cloud Apps.  All rights reserved.
 */

import create from 'zustand';
import { User } from './model/SessionState';
import SessionService from './service/SessionService';
import SettingsService from './service/SettingsService';

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

export interface SettingsState {
  accessToken: string;
  loading: boolean;
  notification: {
    message: string;
    open: boolean;
    duration: number;
  };

  getAccessToken: () => void;
  setAccessToken: (token: string) => void;
  saveToken: () => void;
}

export const useSettingsStore = create<SettingsState>((set, get) => ({
    accessToken: '',
    loading: true,
    notification: {
      open: false,
      duration: 3000,
      message: ''
    },

    getAccessToken: async () => {
      try {
        set({ loading: true });
        const accessToken = await SettingsService.getAccessToken(1);
        set({ accessToken, notification: { message: 'Token retrieved', duration: 3000, open: true } });
      } catch (error) {
        set({ accessToken: '', notification: { message: 'Failed to load token', duration: 3000, open: true } });
      } finally {
        set({ loading: false });
      }
    },

    setAccessToken: (token: string) => {
      set({ accessToken: token });
    },

    saveToken: async () => {
      try {
        const { accessToken } = get();
        const token = await SettingsService.saveAccessToken(1, accessToken);
        set({ accessToken: token, notification: { message: 'Token saved', duration: 3000, open: true } });
      } catch (e) {
        set({ accessToken: '', notification: { message: 'Failed to save token', duration: 3000, open: true } });
      }
    }
  }
));
