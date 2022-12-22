import axios from "axios";
import {Organization} from "../model/Organization";


class OrganizationService {

    async saveOrganization(): Promise<Organization> {
        const response = await axios.post<Organization>(
            `http://localhost:8080/api/v1/organizations/save?organizationName=gfdsg`,
            {withCredentials: true});
        return response.data;
    }
}

export default new OrganizationService();
