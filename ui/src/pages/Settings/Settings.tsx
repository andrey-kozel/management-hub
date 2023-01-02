import {Button, Container, TextField} from "@mui/material";
import {useState} from "react";
import {getAccessToken, saveAccessToken} from "../../service/SettingsService";
import AccessToken from "../../components/AccessToken";

const Settings = () => {
    const [accessToken, setAccessToken] = useState('');
    const [existingAccessToken, setExistingAccessToken] = useState('');

    const saveToken = () => {
        saveAccessToken(1, accessToken);
    }

    const clearInput = () => {
        setAccessToken('');
    }

    const updateExistingAccessToken = () => {
        getAccessToken(1).then(r => setExistingAccessToken(r));
    }

    updateExistingAccessToken();

    return (
        <div>
            <Container>
                <AccessToken token={existingAccessToken}/>
                <TextField
                    id="outlined-basic"
                    label="Access token"
                    variant="outlined"
                    fullWidth
                    multiline
                    value={accessToken}
                    onChange={e => setAccessToken(e.target.value)}
                    sx={{marginTop: 1}}
                />
                <Button
                    variant="contained"
                    sx={{marginTop: 3}}
                    onClick={() => {
                        saveToken();
                        updateExistingAccessToken();
                        clearInput();
                    }}
                >
                    Save
                </Button>
            </Container>
        </div>
    );
};

export default Settings;