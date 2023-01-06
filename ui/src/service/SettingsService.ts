import axios, {AxiosError} from "axios";

export const saveAccessToken = (organizationId: number, accessToken: string) => {
    try {
        axios.post('http://localhost:8080/api/v1/github-service/organization-settings/access-token/',
            {organizationId, accessToken}, {
                withCredentials: true,
                xsrfCookieName: "MH-XSRF",
                xsrfHeaderName: "MH-X-XSRF"
            });
    } catch (e: unknown) {
        const error = e as AxiosError;
        alert(error.message);
    }
}

export const getAccessToken = async (organizationId: number) => {
    try {
        const response = await axios.get('http://localhost:8080/api/v1/github-service/organization-settings/access-token/'
            + organizationId, {withCredentials: true});
        return response.data.accessToken;
    } catch (e: unknown) {
        const error = e as AxiosError;
        alert(error.message);
    }
}
