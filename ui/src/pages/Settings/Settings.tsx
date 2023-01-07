import {Alert, Button, Container, TextField} from "@mui/material";
import React, {useEffect, useState} from "react";
import AccessToken from "../../components/AccessToken";
import SettingsService from "../../service/SettingsService";
import {useSettingsStore} from "../../store";

const Settings = () => {
    const [accessToken, setAccessToken] = useState('');
    const [existingAccessToken, setExistingAccessToken] = useState('');
    const [error, setError] = useState(false);
    const [success, setSuccess] = useState(false);

    const errorText: string = 'Access token min length is 5 characters!';
    const successText: string = 'Access token saved successfully!';

    const getAccessToken = useSettingsStore(state => state.getAccessToken);
    const existingAccessTokenFromStore = useSettingsStore(state => state.accessToken);

    useEffect(() => {
        getAccessToken();
    }, [])


    const saveToken = () => {
        SettingsService.saveAccessToken(1, accessToken).then(t => setExistingAccessToken(t));
    }

    const handleSubmit = () => {
        if (accessToken.length >= 5) {
            saveToken();
            setAccessToken('');
            setError(false);
            setSuccess(true);
            return;
        }
        setError(true);
        setSuccess(false);
    }

    return (
        <>
            <Container>
                {success ?
                    <Alert
                        severity="success"
                        onClose={() => setSuccess(false)}
                    >{successText}</Alert>
                    : <></>
                }
                {error ?
                    <Alert
                        severity="error"
                        onClose={() => setError(false)}
                    >{errorText}</Alert>
                    : <></>
                }
                {existingAccessToken ?
                    <AccessToken token={existingAccessToken}/> :
                    <AccessToken token={existingAccessTokenFromStore}/>
                }
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
        </>
    );
};

export default Settings;