import React from "react";
import { useState, useEffect } from "react";
import { Typography, Box, TextField, Button } from "@mui/material";
import { Autocomplete } from "@mui/material";

interface IProps {
  updateData: any;
  dataToEdit: any;
  setDataToEdit: any;
}

const order = {
  userEmail: "",
  price: "",
  status: "",
};

const UpdateOrders: React.FC<IProps> = ({
  updateData,
  dataToEdit,
  setDataToEdit,
}) => {
  const [dataOrder, setDataOrder] = useState(order);

  useEffect(() => {
    dataToEdit ? setDataOrder(dataToEdit) : setDataOrder(order);
  }, [dataToEdit]);
  return <div></div>;
};

export default UpdateOrders;

/*

const FormItems: React.FC<IProps> = ({
  categories,
  createData,
  updateData,
  dataToEdit,
  setDataToEdit,
}) => {
  const [dataItem, setDataItem] = useState(item);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleClickFileChooser = () => {
    if (fileInputRef.current) {
      fileInputRef.current.click();
    }
  };

  const handleFileChange = (event: any) => {
    const file = event.target.files?.[0];
    const path = URL.createObjectURL(file);
    setDataItem({ ...dataItem, imageUrl: path });
  };

  useEffect(() => {
    dataToEdit ? setDataItem(dataToEdit) : setDataItem(item);
  }, [dataToEdit]);

  const handleItem = (e: any) => {
    e.preventDefault();
    if (
      !dataItem.name ||
      !dataItem.description ||
      !dataItem.price ||
      !dataItem.category ||
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
    setDataItem({ ...dataItem, category: e });
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
              value={dataItem.category || null}
            />
          )}
          <Button
            onClick={handleClickFileChooser}
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
          <input
            type="file"
            accept="image/jpeg, image/png"
            ref={fileInputRef}
            style={{ display: "none" }}
            onChange={handleFileChange}
          />

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
*/
