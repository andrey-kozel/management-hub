import axios, {AxiosError} from "axios";

class SettingsService {
    saveAccessToken = async (organizationId: number, accessToken: string) => {
        try {
            const response = await axios.post('http://localhost:8080/api/v1/github-service/organization-settings/access-token/',
                {organizationId, accessToken}, {
                    withCredentials: true,
                    xsrfCookieName: "MH-XSRF",
                    xsrfHeaderName: "MH-X-XSRF"
                });
            return response.data.accessToken;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    getAccessToken = async (organizationId: number) => {
        try {
            const response =
                await axios.post('http://localhost:8080/api/v1/github-service/organization-settings/access-token/existing',
                    {organizationId}, {
                        withCredentials: true,
                        xsrfCookieName: "MH-XSRF",
                        xsrfHeaderName: "MH-X-XSRF"
                    });
            return response.data.accessToken;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}

export default new SettingsService();

