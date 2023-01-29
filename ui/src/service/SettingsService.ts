import axios, {AxiosError} from "axios";

class SettingsService {
    saveAccessToken = async (accessToken: string) => {
        try {
            const response = await axios.post('http://localhost:8080/api/v1/organization/settings/',
                {accessToken}, {
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

    getAccessToken = async () => {
        try {
            const response =
                await axios.get('http://localhost:8080/api/v1/organization/settings/',
                    {
                        withCredentials: true,
                        xsrfCookieName: "MH-XSRF",
                        xsrfHeaderName: "MH-X-XSRF"
                    });
            console.log("token: " + response);
            return response.data.accessToken;
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }
}

export default new SettingsService();

