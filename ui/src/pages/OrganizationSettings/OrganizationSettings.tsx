import {Button, Container, TextField} from "@mui/material";
import {useState} from "react";
import {saveAccessToken, useToken} from "../../api/OrganizationSettings";
import AccessToken from "../../components/AccessToken";

const OrganizationSettings = () => {
    const [token, setToken] = useState('');

    const saveToken = () => {
        saveAccessToken(token);
    }

    const clearInput = () => {
        setToken('');
    }

    const {existingToken} = useToken(1);

    return (
        <div>
            <Container>
                <AccessToken token={existingToken}/>
                <TextField
                    id="outlined-basic"
                    label="Access token"
                    variant="outlined"
                    fullWidth
                    multiline
                    onChange={e => setToken(e.target.value)}
                />
                <Button
                    variant="contained"
                    sx={{marginTop: 3}}
                    onClick={() => {
                        saveToken();
                        clearInput();
                    }}
                >
                    Save
                </Button>
            </Container>
        </div>
    );
};

export default OrganizationSettings;