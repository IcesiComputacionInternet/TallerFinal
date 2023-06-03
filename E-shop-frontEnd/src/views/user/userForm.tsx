import { Typography, TextField, ThemeProvider, Container } from "@mui/material";
import { Box, createTheme, FormControl, InputLabel } from "@mui/material";
import { OutlinedInput, Button, Autocomplete } from "@mui/material";
import { InputAdornment, IconButton } from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { useState } from "react";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";

const form = {
  email: "",
  password: "",
  phoneNumber: "",
  firstName: "",
  lastName: "",
  address: "",
  birthDate: "",
};

const UserForm = () => {
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

  const [dataUser, setDataUser] = useState(form);
  const [password, setPassword] = useState(form.password);

  const [showPassword, setShowPassword] = useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    event.preventDefault();
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
          <Box component="form" noValidate sx={{ mt: 1 }}>
            <Typography
              variant="h4"
              component="h4"
              sx={{ textAlign: "center" }}
            >
              Crea una cuenta
            </Typography>
            <TextField
              color="primary"
              margin="normal"
              fullWidth
              id="username"
              label="Usuario"
              name="username"
              autoFocus
              sx={{ mb: 3 }}
            />
            <FormControl
              fullWidth
              variant="outlined"
              color="primary"
              sx={{ mb: 1 }}
            >
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
              />
            </FormControl>
            <TextField
              color="primary"
              margin="normal"
              fullWidth
              id="phoneNumber"
              label="Número de teléfono"
              name="phoneNumber"
              autoFocus
            />
            <TextField
              color="primary"
              margin="normal"
              fullWidth
              id="firstName"
              label="Nombres"
              name="firstName"
              autoFocus
            />
            <TextField
              color="primary"
              margin="normal"
              fullWidth
              id="lastName"
              label="Apellidos"
              name="lastName"
              autoFocus
            />
            <TextField
              color="primary"
              margin="normal"
              fullWidth
              id="address"
              label="Dirección"
              name="address"
              autoFocus
              sx={{ mb: 3 }}
            />
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={["Hola Mundo", "Hola Mundo 2"]}
              sx={{ width: "100%", mb: 3 }}
              renderInput={(params) => <TextField {...params} label="Rol" />}
            />
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker label="Fecha de nacimiento" sx={{ width: "100%" }} />
            </LocalizationProvider>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{
                mt: 3,
                mb: 5,
                bgcolor: "#D3D3D3",
                color: "black",
                "&:hover": {
                  bgcolor: "black",
                  color: "white",
                  transition: "0.5s",
                },
              }}
            >
              Crear cuenta
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default UserForm;
