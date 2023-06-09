import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


const theme = createTheme();

const baseUrl = "http://localhost:8080";

export default function Register() {

  const navigate = useNavigate();
  
  const[firstName, setFirstName] = useState("");
  const[lastName, setLastName] = useState("");
  const[address, setAddress] = useState("");
  const[birthDay, setBirthDay] = useState("");
  const[username, setUsername] = useState("");
  const[password, setPassword] = useState("");
  const[confirmPassword, setConfirmPassword] = useState("");
  const[phoneNumber, setPhoneNumber] = useState("");
  const role = "client";

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (password !== confirmPassword) {
      alert("Passwords don't match");
      return;
    }

    if (username.length == 0 && password.length == 0 && confirmPassword.length == 0 && phoneNumber.length == 0) {
      alert("Please fill all the fields");
      return;
    }
    await createUser();

  };

  const createUser = async () => {
    try {
      const response = await axios.post(baseUrl+'/api/users/add', {
        firstName,
        lastName,
        address,
        birthDay,
        username,
        password,
        phoneNumber,
        role,
      });
  
      // Resto del código si la petición es exitosa
    } catch (error) {
      console.error(error);
      alert("Se produjo un error al crear el usuario");
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign up
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
            <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="firstname"
                  label="First Name"
                  name="firstname"
                  onChange={(event)=> setFirstName(event.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="lastname"
                  label="Last Name"
                  name="lastname"
                  onChange={(event)=> setLastName(event.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="birthday"
                  label="Birthday"
                  name="birthday"
                  onChange={(event)=> setBirthDay(event.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="address"
                  label="Address"
                  name="address"
                  onChange={(event)=> setAddress(event.target.value)}
                />
              </Grid>    
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="username"
                  label="Email"
                  name="username"
                  onChange={(event)=> setUsername(event.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  onChange={(event)=> setPassword(event.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="Confirm password"
                  label="Confirm password"
                  type="password"
                  id="confPassword"
                  onChange={(event)=> setConfirmPassword(event.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="Phone number"
                  label="Colombian phone number"
                  id="phoneNumber"
                  onChange={(event)=> setPhoneNumber(event.target.value)}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>
            <Grid container justifyContent="center">
              <Grid item>
                <Link href="/" variant="body2">
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}