import OrganizationService from "../../service/OrganizationService";
import {useEffect, useState} from "react";
import {Organization} from "../../model/Organization";

const Users = () => {
  {/*Удалить*/}
  const [organization, setUsers] = useState<Organization>();
  {/*Удалить*/}
  useEffect(() => {
      OrganizationService.saveOrganization()
          .then(response => setUsers(response));
  }, []);

  return (
      <>
        <div>users</div>
          {/*Удалить*/}
          <div>
              <span>{organization?.id}</span>
              <span>{organization?.name}</span>
          </div>
      </>
  );
};

export default Users;
