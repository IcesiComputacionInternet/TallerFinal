import { Box, TextField, Button, Typography } from "@mui/material";
import { useState, useEffect } from "react";
import "./roles.css";
import React from "react";

const role = {
  roleName: "",
  description: "",
};

interface IProps {
  createData: any;
  updateData: any;
  dataToEdit: any;
  setDataToEdit: any;
}

const AddRole: React.FC<IProps> = ({
  createData,
  updateData,
  dataToEdit,
  setDataToEdit,
}) => {
  const [dataRole, setDataRole] = useState(role);

  useEffect(() => {
    dataToEdit ? setDataRole(dataToEdit) : setDataRole(role);
  }, [dataToEdit]);

  const handleRole = (e: any) => {
    e.preventDefault();

    if (!dataRole.roleName || !dataRole.description) {
      alert("Datos incompletos");
      return;
    }

    if (dataToEdit === null) {
      createData(dataRole);
    } else {
      updateData(dataRole);
    }

    handleReset();
  };

  const handleReset = () => {
    setDataRole(role);
    setDataToEdit(null);
  };

  const handleData = (e: any) => {
    setDataRole({ ...dataRole, [e.target.name]: e.target.value });
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h4"
        sx={{ m: 3, textAlign: "center" }}
      >
        {dataToEdit ? "Editar Rol" : "Agregar Rol"}
      </Typography>
      <Box
        className="container-form-role"
        component="form"
        onSubmit={handleRole}
        noValidate
        sx={{ mt: 1 }}
      >
        <TextField
          color="primary"
          margin="normal"
          id="roleName"
          label="Nombre del rol"
          name="roleName"
          autoFocus
          onChange={handleData}
          value={dataRole.roleName}
        />
        <TextField
          color="primary"
          margin="normal"
          id="description"
          label="Descripción"
          name="description"
          autoFocus
          onChange={handleData}
          value={dataRole.description}
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
          {dataToEdit ? "Editar Rol" : "Agregar Rol"}
        </Button>
      </Box>
    </>
  );
};

export default AddRole;
