import create from 'zustand';
import RepositoryService from "../../service/RepositoryService";
import { Repository } from "../../model/RepositoryState";

export interface RepositoriesState {
    repositories: Repository | null;
    loading: boolean;
    getRepositories: () => void;
}

export const useRepositoriesStore = create<RepositoriesState>(set => ({
    repositories: null,
    loading: true,
    getRepositories: async () => {
        try {
            set({loading: true});
            const repositories = await RepositoryService.getRepositories();
            set({ repositories });
        } catch (error) {
            set({ repositories: null });
        } finally {
            set({ loading: false });
        }

    }
}));
