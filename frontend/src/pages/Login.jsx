import React, { useState } from 'react';
import { TextField, Button, Grid } from '@mui/material';
import { Navigate } from "react-router-dom";

function Login() {
    const [contact, setContact] = useState('');
    const [password, setPassword] = useState('');
    const [log, setLog] = useState(false);

    const handleContactChange = (event) => {
        setContact(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        setLog(true);
        console.log('Contact:', contact);
        console.log('Password:', password);
    };

    return (
    <form onSubmit={handleSubmit}>
        <Grid container justifyContent="center" alignItems="center" style={{ height: '100vh' }}>
            <Grid item>
                <Grid container direction="column" alignItems="center" spacing={2}>
                    <Grid item>
                        <h1>Login</h1>
                    </Grid>
                    <Grid item>
                        <TextField
                            label="Email or Phone Number"
                            type="text"
                            value={contact}
                            onChange={handleContactChange}
                            required
                        />
                    </Grid>
                    <Grid item>
                        <TextField
                            label="Password"
                            type="password"
                            value={password}
                            onChange={handlePasswordChange}
                            required
                        />
                    </Grid>
                    <Grid item>
                        <Button type="submit" variant="contained" color="primary" fullWidth>
                            Login
                        </Button>
                        {log && <Navigate to={"/home"}/>}
                    </Grid>
                </Grid>
            </Grid>
        </Grid>
    </form>
    );
}

export default Login;