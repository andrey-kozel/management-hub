import {Button, Container, TextField} from "@mui/material";
import {useState} from "react";
import {getAccessToken, saveAccessToken} from "../../service/SettingsService";
import AccessToken from "../../components/AccessToken";

const Settings = () => {
    const [accessToken, setAccessToken] = useState('');
    const [existingAccessToken, setExistingAccessToken] = useState('');
    const errorText: string = 'Access token min length is 5 characters!';

    const saveToken = () => {
        saveAccessToken(1, accessToken);
    }

    const clearInput = () => {
        setAccessToken('');
    }

    const updateExistingAccessToken = () => {
        getAccessToken(1).then(r => setExistingAccessToken(r));
    }

    const validateTokenLength = () => {
        return accessToken.length >= 5;
    }

    const handleSubmit = () => {
        if (validateTokenLength()) {
            saveToken();
            updateExistingAccessToken();
            clearInput();
            return;
        }
        alert(errorText);
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
                    helperText="5 charecters min"
                    onChange={e => setAccessToken(e.target.value)}
                    sx={{marginTop: 1}}
                />
                <Button
                    variant="contained"
                    sx={{marginTop: 3}}
                    onClick={() => handleSubmit()}
                >
                    Save
                </Button>
            </Container>
        </div>
    );
};

export default Settings;