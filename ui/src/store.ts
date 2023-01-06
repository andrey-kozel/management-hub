/*
 * Copyright (C) 2022 Spanning Cloud Apps.  All rights reserved.
 */

import create from 'zustand';
import { User } from './model/SessionState';
import SessionService from './service/SessionService';
import SettingsService from "./service/SettingsService";

export interface SessionState {
  user: User | null;
  loading: boolean;
  getSession: () => void;
}

export interface SettingsState {
    accessToken: string;
    loading: boolean;

    getAccessToken: () => void;
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

export const useSettingsStore = create<SettingsState>(set => ({
        accessToken: '',
        loading: true,
        getAccessToken: async () => {
            try {
                const accessToken = await SettingsService.getAccessToken(1);
                set({accessToken});
                console.log(accessToken);
            } catch (error) {
                set({accessToken: ''})
            } finally {
                set({loading: false})
            }
        }
    }
))