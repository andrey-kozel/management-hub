import { User } from '../model/SessionState';
import axios from 'axios';

class SessionService {

  async getSession(): Promise<User> {
    const response = await axios.get<User>('http://localhost:8080/api/v1/sessions', { withCredentials: true });
    return response.data;
  }

}

export default new SessionService();
