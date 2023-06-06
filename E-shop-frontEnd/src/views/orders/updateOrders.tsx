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
  id: "",
  userEmail: "",
  total: "",
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

  const handleOrder = (e: any) => {
    e.preventDefault();
    updateData(dataOrder);
    handleReset();
  };

  const handleData = (e: any) => {
    setDataOrder({ ...dataOrder, status: e });
  };

  const handleReset = () => {
    setDataOrder(order);
    setDataToEdit(null);
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h4"
        sx={{ m: 3, textAlign: "center" }}
      >
        Cambiar estado de orden
      </Typography>
      <Box
        className="container-form-items"
        component="form"
        onSubmit={handleOrder}
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
            disabled={true}
            color="primary"
            margin="normal"
            id="userEmail"
            label="Email del usuario"
            name="userEmail"
            autoFocus
            onChange={handleData}
            value={dataOrder.userEmail}
          />
          <TextField
            disabled={true}
            color="primary"
            margin="normal"
            id="total"
            label="Precio"
            name="total"
            autoFocus
            onChange={handleData}
            value={dataOrder.total}
          />
          {dataToEdit && (
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={["PENDING", "PAID", "SENT", "RECEIVED"]}
              sx={{ width: "23%", mt: 1 }}
              onChange={(_event, newValue) => {
                handleData(newValue);
              }}
              renderInput={(params) => (
                <TextField {...params} label="Categorias" />
              )}
              value={dataOrder.status || null}
            />
          )}
        </div>
        <div
          style={{
            display: "flex",
            alignItems: "center",
            margin: "5px auto",
            justifyContent: "space-around",
            width: "60%",
          }}
        >
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
            Cambiar estado
          </Button>
          {dataToEdit && (
            <Button
              onClick={handleReset}
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
              Limpiar
            </Button>
          )}
        </div>
      </Box>
    </>
  );
};

export default UpdateOrders;
