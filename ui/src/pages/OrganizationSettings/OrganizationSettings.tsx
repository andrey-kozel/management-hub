import {Button, Container, TextField} from "@mui/material";

const OrganizationSettings = () => {
    return (
        <div>
            <Container>
                <TextField
                    id="outlined-basic"
                    label="Access token"
                    variant="outlined"
                    fullWidth
                    multiline
                />
                <Button
                    variant="contained"
                    sx={{marginTop: 3}}
                >
                    Save
                </Button>
            </Container>
        </div>
    );
};

export default OrganizationSettings;