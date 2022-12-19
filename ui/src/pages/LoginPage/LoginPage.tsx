import { Avatar, Box, Button, Container, Typography } from '@mui/material';
import { LockOutlined } from '@mui/icons-material';

const LoginPage = () => {
  return (
    <Container component="main" maxWidth="xs">
      <Box sx={{ marginTop: 8, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
          <LockOutlined />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <Button href={`${process.env.REACT_APP_API_GATEWAY_URL}/oauth2/authorization/github`}
                style={{ marginTop: 16}}
                variant="contained">
          Authorize with github
        </Button>
      </Box>
    </Container>
  );
};

export default LoginPage;
