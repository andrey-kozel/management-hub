import { Button, Container, Snackbar, TextField } from '@mui/material';
import React, { useEffect } from 'react';
import AccessToken from '../../components/AccessToken';
import { useSettingsStore } from '../../store';

const Settings = () => {
  const accessToken = useSettingsStore(state => state.accessToken);
  const notification = useSettingsStore(state => state.notification);

  const getAccessToken = useSettingsStore(state => state.getAccessToken);
  const setAccessToken = useSettingsStore(state => state.setAccessToken);
  const saveToken = useSettingsStore(state => state.saveToken);

  useEffect(() => {
    getAccessToken();
  }, []);

  return (
    <>
      <Container>
        <AccessToken token={accessToken} /> :
        <TextField
          id="outlined-basic"
          label="Access token"
          variant="outlined"
          fullWidth
          multiline
          value={accessToken}
          helperText="5 charecters min"
          onChange={e => setAccessToken(e.target.value)}
          sx={{ marginTop: 1 }}
        />
        <Button
          variant="contained"
          sx={{ marginTop: 3 }}
          disabled={accessToken.length < 5}
          onClick={() => saveToken()}
        >
          Save
        </Button>
        <Snackbar
          open={notification.open}
          autoHideDuration={notification.duration}
          message={notification.message}
        />
      </Container>
    </>
  );
};

export default Settings;
