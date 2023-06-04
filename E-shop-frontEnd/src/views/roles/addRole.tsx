import { Box, TextField, Button } from "@mui/material";
import { useState } from "react";
import "./roles.css";

const role = {
  name: "",
  description: "",
};

const AddRole = () => {
  const [dataRole, setDataRole] = useState(role);

  const handleAddRole = (e: any) => {
    e.preventDefault();
  };

  const handleData = (e: any) => {
    setDataRole({ ...dataRole, [e.target.name]: e.target.value });
  };

  return (
    <Box
      className="container-form-role"
      component="form"
      onSubmit={handleAddRole}
      noValidate
      sx={{ mt: 1 }}
    >
      <TextField
        color="primary"
        margin="normal"
        id="name"
        label="Nombre del rol"
        name="name"
        autoFocus
        onChange={handleData}
      />
      <TextField
        color="primary"
        margin="normal"
        id="description"
        label="Descripción"
        name="description"
        autoFocus
        onChange={handleData}
      />
      <Button
        type="submit"
        variant="contained"
        sx={{
          height: "55px",
          mt: 2,
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
        Crear nuevo rol
      </Button>
    </Box>
  );
};

export default AddRole;
