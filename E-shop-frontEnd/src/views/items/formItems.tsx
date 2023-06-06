import React from "react";
import { useState, useEffect } from "react";
import { Typography, Box, TextField, Button } from "@mui/material";
import { Autocomplete } from "@mui/material";

const item = {
  name: "",
  description: "",
  price: "",
  categoryId: "",
  imageUrl: "",
};

interface IProps {
  categories: any;
  createData: any;
  updateData: any;
  dataToEdit: any;
  setDataToEdit: any;
}

const FormItems: React.FC<IProps> = ({
  categories,
  createData,
  updateData,
  dataToEdit,
  setDataToEdit,
}) => {
  const [dataItem, setDataItem] = useState(item);

  useEffect(() => {
    dataToEdit ? setDataItem(dataToEdit) : setDataItem(item);
  }, [dataToEdit]);

  const handleItem = (e: any) => {
    e.preventDefault();
    if (
      !dataItem.name ||
      !dataItem.description ||
      !dataItem.price ||
      !dataItem.categoryId ||
      !dataItem.imageUrl
    ) {
      alert("Datos incompletos");
      return;
    }

    if (dataToEdit === null) {
      createData(dataItem);
    } else {
      updateData(dataItem);
    }

    handleReset();
  };

  const handleData = (e: any) => {
    setDataItem({ ...dataItem, [e.target.name]: e.target.value });
  };

  const handleCategories = (e: any) => {
    setDataItem({ ...dataItem, categoryId: e });
  };

  const handleReset = () => {
    setDataItem(item);
    setDataToEdit(null);
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h4"
        sx={{ m: 3, textAlign: "center" }}
      >
        {dataToEdit ? "Editar CD" : "Agregar CD"}
      </Typography>
      <Box
        className="container-form-items"
        component="form"
        onSubmit={handleItem}
        noValidate
        sx={{ mt: 1 }}
      >
        <div
          style={{
            display: "flex",
            alignItems: "center",
            margin: "5px auto",
            justifyContent: "space-between",
            width: "60%",
          }}
        >
          <TextField
            disabled={dataToEdit ? true : false}
            color="primary"
            margin="normal"
            id="name"
            label="Nombre del item"
            name="name"
            autoFocus
            onChange={handleData}
            value={dataItem.name}
          />
          <TextField
            color="primary"
            margin="normal"
            id="description"
            label="Descripción"
            name="description"
            autoFocus
            onChange={handleData}
            value={dataItem.description}
          />
          <TextField
            type="number"
            color="primary"
            margin="normal"
            id="price"
            label="Precio"
            name="price"
            autoFocus
            onChange={handleData}
            value={dataItem.price}
          />
        </div>
        <div
          style={{
            display: "flex",
            alignItems: "center",
            margin: "5px auto",
            justifyContent: "space-between",
            width: "60%",
          }}
        >
          {categories && (
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={categories}
              sx={{ width: "23%", mt: 2 }}
              onChange={(_event, newValue) => {
                handleCategories(newValue);
              }}
              renderInput={(params) => (
                <TextField {...params} label="Categorias" />
              )}
            />
          )}
          <Button
            variant="contained"
            sx={{
              height: "55px",
              mt: 2,
              mb: 2,
              width: "23%",
              bgcolor: "#D3D3D3",
              color: "black",
              "&:hover": {
                bgcolor: "black",
                color: "white",
                transition: "0.5s",
              },
            }}
          >
            {dataToEdit ? "Editar Imagen" : "Agregar Imagen"}
          </Button>
          <Button
            type="submit"
            variant="contained"
            sx={{
              height: "55px",
              width: "23%",
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
            {dataToEdit ? "Editar CD" : "Agregar CD"}
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
        </div>
      </Box>
    </>
  );
};

export default FormItems;
