import React, { useState } from 'react';
import { TextField, Button, Grid } from '@mui/material';

function Login() {
  const [contact, setContact] = useState('');
  const [password, setPassword] = useState('');

  const handleContactChange = (event) => {
    setContact(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

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
                    </Grid>
                </Grid>
            </Grid>
        </Grid>
    </form>
  );
}

export default Login;