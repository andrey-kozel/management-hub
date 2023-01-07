import {Alert, Button, Container, Snackbar, TextField} from "@mui/material";
import React, {useEffect} from "react";
import AccessToken from "../../components/AccessToken";
import {useSettingsStore} from "./store";

const Settings = () => {
    const accessToken = useSettingsStore(state => state.accessToken);
    const existingToken = useSettingsStore(state => state.existingToken);
    const notification = useSettingsStore(state => state.notification);

    const getAccessToken = useSettingsStore(state => state.getAccessToken);
    const setAccessToken = useSettingsStore(state => state.setAccessToken);
    const saveToken = useSettingsStore(state => state.saveToken);
    const closeNotification = useSettingsStore(state => state.closeNotification);

    useEffect(() => {
        getAccessToken();
    }, [])


    return (
        <>
            <Container>
                <AccessToken token={existingToken}/>
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
                    disabled={accessToken.length < 5}
                    onClick={saveToken}
                >
                    Save
                </Button>
                <Snackbar
                    open={notification.open}
                    autoHideDuration={notification.duration}
                    onClose={closeNotification}
                >
                    <Alert
                        severity={notification.severity}
                    >
                        {notification.message}
                    </Alert>
                </Snackbar>
            </Container>
        </>
    );
};

export default Settings;