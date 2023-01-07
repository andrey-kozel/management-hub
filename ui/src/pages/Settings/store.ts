import create from "zustand";
import SettingsService from "../../service/SettingsService";

export interface SettingsState {
    accessToken: string;
    loading: boolean;
    existingToken: string;
    notification: {
        message: string;
        open: boolean;
        duration: number;
        severity: any
    }
    getAccessToken: () => void;
    setAccessToken: (token: string) => void;
    saveToken: () => void;
    closeNotification: () => void;
}

export const useSettingsStore = create<SettingsState>((set, get) => ({
    accessToken: '',
    loading: true,
    existingToken: '',
    notification: {
        message: '',
        open: false,
        duration: 1500,
        severity: ''
    },
    getAccessToken: async () => {
        try {
            set({loading: true})
            const accessToken = await SettingsService.getAccessToken(1);
            set({
                existingToken: accessToken,
                notification: {
                    message: 'Token retrieved',
                    duration: 1500,
                    open: true,
                    severity: 'success'
                }
            });
        } catch (error) {
            set({
                accessToken: '',
                notification: {
                    message: 'Failed to load token',
                    duration: 1500,
                    open: true,
                    severity: 'error'
                }
            })
        } finally {
            set({loading: false});
        }
    },
    setAccessToken: async (token: string) => {
        set({accessToken: token});
    },
    saveToken: async () => {
        try {
            const {accessToken} = get();
            const token = await SettingsService.saveAccessToken(1, accessToken);
            set({
                existingToken: token,
                accessToken: '',
                loading: true,
                notification: {
                    message: 'Token saved',
                    duration: 1500, open: true,
                    severity: 'success'
                }
            })
        } catch (error) {
            set({
                accessToken: '', notification: {
                    message: 'Failed to save token',
                    duration: 1500, open: true,
                    severity: 'error'
                }
            })
        } finally {
            set({loading: false});
        }
    },
    closeNotification: async () => {
        set({
            notification: {
                open: false,
                duration: 0,
                severity: '',
                message: ''
            }
        });
    }
}))
