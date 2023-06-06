import { Box, TextField, Button, Typography } from "@mui/material";
import { useState, useEffect } from "react";
import React from "react";

const category = {
  name: "",
  description: "",
};

interface IProps {
  createData: any;
  updateData: any;
  dataToEdit: any;
  setDataToEdit: any;
}

const AddCategory: React.FC<IProps> = ({
  createData,
  updateData,
  dataToEdit,
  setDataToEdit,
}) => {
  const [dataCategory, setDataCategory] = useState(category);

  useEffect(() => {
    dataToEdit ? setDataCategory(dataToEdit) : setDataCategory(category);
  }, [dataToEdit]);

  const handleCategory = (e: any) => {
    e.preventDefault();

    if (!dataCategory.name || !dataCategory.description) {
      alert("Datos incompletos");
      return;
    }

    if (dataToEdit === null) {
      createData(dataCategory);
    } else {
      updateData(dataCategory);
    }

    handleReset();
  };

  const handleReset = () => {
    setDataCategory(category);
    setDataToEdit(null);
  };

  const handleData = (e: any) => {
    setDataCategory({ ...dataCategory, [e.target.name]: e.target.value });
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h4"
        sx={{ m: 3, textAlign: "center" }}
      >
        {dataToEdit ? "Editar Categoria" : "Agregar Categoria"}
      </Typography>
      <Box
        className="container-form-role"
        component="form"
        onSubmit={handleCategory}
        noValidate
        sx={{ mt: 1 }}
      >
        <TextField
          disabled={dataToEdit ? true : false}
          color="primary"
          margin="normal"
          id="name"
          label="Nombre del rol"
          name="name"
          autoFocus
          onChange={handleData}
          value={dataCategory.name}
        />
        <TextField
          color="primary"
          margin="normal"
          id="description"
          label="Descripción"
          name="description"
          autoFocus
          onChange={handleData}
          value={dataCategory.description}
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
          {dataToEdit ? "Editar Categoria" : "Agregar Categoria"}
        </Button>
        {dataToEdit && (
          <Button
            onClick={handleReset}
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
            Limpiar
          </Button>
        )}
      </Box>
    </>
  );
};

export default AddCategory;
