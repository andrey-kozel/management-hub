import axios, { AxiosError } from 'axios';

class SettingsService {
  saveAccessToken = async (organizationId: number, accessToken: string) => {
    const response = await axios.post('http://localhost:8080/api/v1/github-service/organization-settings/access-token/',
      { organizationId, accessToken }, {
        withCredentials: true,
        xsrfCookieName: 'MH-XSRF',
        xsrfHeaderName: 'MH-X-XSRF'
      });
    return response.data.accessToken;
  };

  getAccessToken = async (organizationId: number) => {
    const response =
      await axios.post('http://localhost:8080/api/v1/github-service/organization-settings/access-token/existing',
        { organizationId }, {
          withCredentials: true,
          xsrfCookieName: 'MH-XSRF',
          xsrfHeaderName: 'MH-X-XSRF'
        });
    return response.data.accessToken;
  };
}

export default new SettingsService();

