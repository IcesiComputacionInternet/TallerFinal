import * as React from "react";
import { TextField, Container, Box, Button } from "@mui/material";
import { FormControl, InputLabel, OutlinedInput } from "@mui/material";
import { InputAdornment, IconButton } from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { createTheme, ThemeProvider } from "@mui/material/styles";

import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.css";
import authServices from "../../services/authServices";
import Swal from "sweetalert2";

const credentials = {
  username: "",
  password: "",
};

const LoginForm = () => {
  const theme = createTheme({
    palette: {
      primary: {
        main: "#3d3d3d",
      },
      secondary: {
        main: "#3d3d3d",
      },
    },
  });

  const [username, setUsername] = useState(credentials.username);
  const [password, setPassword] = useState(credentials.password);
  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    event.preventDefault();
  };

  const handleUsername = (e: any) => {
    setUsername(e.target.value);
  };

  const handlePassword = (e: any) => {
    setPassword(e.target.value);
  };

  const handleLogin = async (e: any) => {
    e.preventDefault();

    try {
      const user = await authServices.login({ username, password });
      localStorage.setItem("token", JSON.stringify(user.data.token));
      localStorage.setItem("role", JSON.stringify(user.data.role));
      localStorage.setItem("username", JSON.stringify(username));
      navigate("/", { replace: true });
    } catch (error) {
      Swal.fire({
        icon: "error",
        title:
          '<span style="font-family: Arial, sans-serif;">Error al autenticar</span>',
        html: '<div style="font-family: Arial, sans-serif;">Usuario o contraseña incorrectos</div>',
      });
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Box
            component="form"
            onSubmit={handleLogin}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              color="primary"
              margin="normal"
              fullWidth
              id="username"
              label="Usuario"
              name="username"
              autoFocus
              onChange={handleUsername}
            />
            <FormControl fullWidth variant="outlined" color="primary">
              <InputLabel htmlFor="outlined-adornment-password">
                Password
              </InputLabel>
              <OutlinedInput
                id="outlined-adornment-password"
                type={showPassword ? "text" : "password"}
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="toggle password visibility"
                      onClick={handleClickShowPassword}
                      onMouseDown={handleMouseDownPassword}
                      edge="end"
                    >
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                }
                label="Password"
                onChange={handlePassword}
              />
            </FormControl>

            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{
                mt: 3,
                mb: 2,
                bgcolor: "#D3D3D3",
                color: "black",
                "&:hover": {
                  bgcolor: "black",
                  color: "white",
                  transition: "0.5s",
                },
              }}
            >
              Iniciar Sesión
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default LoginForm;
