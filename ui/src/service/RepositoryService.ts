import axios from 'axios';
import { Repository } from "../model/RepositoryState";

class RepositoryService {

    async getRepositories(): Promise<Repository> {
        const response = await axios.get<Repository>(
            'http://localhost:8080/api/v1/repositories',
            { withCredentials: true }
        );
        return response.data;
    }

}

export default new RepositoryService();
