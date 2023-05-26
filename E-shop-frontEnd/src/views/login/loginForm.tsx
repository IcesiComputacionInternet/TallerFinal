import { TextField, Container, Box, Button } from "@mui/material";
import "./login.css";

const LoginForm = () => {
  return (
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
          <TextField
            margin="normal"
            fullWidth
            id="username"
            label="Usuario"
            name="username"
            autoFocus
            InputLabelProps={{
              style: {
                color: "#3d3d3d",
              },
            }}
            sx={{
              "& .MuiOutlinedInput-root.Mui-focused .MuiOutlinedInput-notchedOutline":
                {
                  borderColor: "#3d3d3d",
                },
            }}
          />
          <TextField
            margin="normal"
            fullWidth
            id="password"
            label="Contraseña"
            type="password"
            autoComplete="current-password"
            autoFocus
            InputLabelProps={{
              style: {
                color: "#3d3d3d",
              },
            }}
            sx={{
              "& .MuiOutlinedInput-root.Mui-focused .MuiOutlinedInput-notchedOutline":
                {
                  borderColor: "#3d3d3d",
                },
            }}
          />

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
  );
};

export default LoginForm;
