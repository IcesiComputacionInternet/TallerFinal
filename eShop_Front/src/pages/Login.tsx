import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import HomeIcon from "@mui/icons-material/Home";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useState } from "react";
import axios, { AxiosResponse } from "axios";
import { useNavigate, NavigateFunction } from "react-router-dom";

const theme = createTheme();

const baseUrl = "http://localhost:8080";

interface Props {
  setLogin: (isLogged: boolean) => void;
}

const Login = ({ setLogin }: Props) => {
  localStorage.clear();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigation: NavigateFunction = useNavigate();

  const handleSubmit = async (event: any) => {
    event.preventDefault();

    const userCredentials = {
      username: username,
      password: password,
    };
    console.log(userCredentials);

    try {
      const response = await axios.post(
        baseUrl + "/auth",
        {
          username,
          password,
        },
        {
          headers: {
            "Access-Control-Allow-Origin": baseUrl,
          },
        }
      );
      if (response.status === 200) {
        localStorage.setItem("jwt", response.data.token);
        navigation("/home");
      } else {
        alert("Username or password incorrect");
      }
    } catch (error) {
      alert("Username or password incorrect");
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <HomeIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="username"
              label="Username"
              name="username"
              autoComplete="username"
              autoFocus
              onChange={(event) => setUsername(event.target.value)}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              onChange={(event) => setPassword(event.target.value)}
            />

            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={handleSubmit}
            >
              Sign In
            </Button>
            <Grid container justifyContent="center">
              <Grid item>
                <Link href="/register" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default Login;
