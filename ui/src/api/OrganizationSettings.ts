import axios, {AxiosError} from "axios";
import {useEffect, useState} from "react";

export const saveAccessToken = (accessToken: string) => {
    try {
        let organizationId = 1;
        axios.post('http://localhost:8080/api/v1/github-service/organization-settings/access-token/',
            {organizationId, accessToken});
    } catch (e: unknown) {
        const error = e as AxiosError;
        alert(error.message);
    }
}

export const useToken = (organizationId: number) => {
    const [existingToken, setToken] = useState<string>('');

    async function getToken() {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/github-service/organization-settings/access-token/'
                + organizationId);
            console.log(response.data.accessToken);
            setToken(response.data.accessToken);
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message);
        }
    }

    useEffect(() =>{
        getToken();
    }, [])

    return {existingToken}
}