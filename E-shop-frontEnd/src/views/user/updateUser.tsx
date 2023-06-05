import React from "react";
import { useState, useEffect } from "react";
import { Typography, Button, Box, TextField } from "@mui/material";
import { Autocomplete } from "@mui/material";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";

dayjs.extend(customParseFormat);

const formUser = {
  email: "",
  phoneNumber: "",
  firstName: "",
  lastName: "",
  address: "",
  birthDate: "",
  role: "",
};

interface IProps {
  dataRoles: any;
  updateData: any;
  dataToEdit: any;
  setDataToEdit: any;
}

const UpdateUser: React.FC<IProps> = ({
  dataRoles,
  updateData,
  dataToEdit,
  setDataToEdit,
}) => {
  const [dataUser, setDataUser] = useState(formUser);

  useEffect(() => {
    dataToEdit ? setDataUser(dataToEdit) : setDataUser(formUser);
  }, [dataToEdit]);

  const handleRole = (role: any) => {
    setDataUser({ ...dataUser, role: role });
  };

  const handleDate = (date: Date) => {
    const fecha = new Date(date.toString());

    const newDate = `${fecha.getDate()}/${
      fecha.getMonth() + 1
    }/${fecha.getFullYear()}`;

    setDataUser({ ...dataUser, birthDate: newDate });
  };

  const handleData = (e: any) => {
    setDataUser({ ...dataUser, [e.target.name]: e.target.value });
  };

  const handleUser = (e: any) => {
    e.preventDefault();

    if (
      !dataUser.email ||
      !dataUser.lastName ||
      !dataUser.phoneNumber ||
      !dataUser.firstName ||
      !dataUser.address ||
      !dataUser.birthDate ||
      !dataUser.role
    ) {
      alert("Datos incompletos");
      return;
    }
    updateData(dataUser);
    handleReset();
  };

  const handleReset = () => {
    setDataUser(formUser);
    setDataToEdit(null);
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h4"
        sx={{ m: 3, textAlign: "center" }}
      >
        Actualizar usuario
      </Typography>
      <Box
        className="container-form-user"
        component="form"
        onSubmit={handleUser}
        noValidate
        sx={{ mt: 1 }}
      >
        <div
          className="container-form-user-top"
          style={{
            display: "flex",
            alignItems: "center",
            margin: "5px auto",
            justifyContent: "space-between",
            width: "70%",
          }}
        >
          <TextField
            disabled={true}
            color="primary"
            margin="normal"
            id="email"
            label="Email"
            name="email"
            autoFocus
            onChange={handleData}
            value={dataUser.email}
          />
          <TextField
            color="primary"
            margin="normal"
            id="phoneNumber"
            label="Telefono"
            name="phoneNumber"
            autoFocus
            onChange={handleData}
            value={dataUser.phoneNumber}
          />
          <TextField
            color="primary"
            margin="normal"
            id="firstName"
            label="Nombre"
            name="firstName"
            autoFocus
            onChange={handleData}
            value={dataUser.firstName}
          />
          <TextField
            color="primary"
            margin="normal"
            id="lastName"
            label="Apellido"
            name="lastName"
            autoFocus
            onChange={handleData}
            value={dataUser.lastName}
          />
        </div>
        <div
          className="container-form-user-bottom"
          style={{
            display: "flex",
            alignItems: "center",
            margin: "5px auto",
            justifyContent: "space-between",
            width: "70%",
          }}
        >
          <TextField
            color="primary"
            margin="normal"
            id="address"
            label="Dirección"
            name="address"
            autoFocus
            onChange={handleData}
            value={dataUser.address}
          />
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              label="Fecha de nacimiento"
              sx={{ width: "20%", mt: 1 }}
              onChange={(date) => handleDate(date as Date)}
            />
          </LocalizationProvider>
          {dataUser.role && (
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={dataRoles}
              sx={{ width: "20%", mt: 1 }}
              onChange={(_event, newValue) => {
                handleRole(newValue);
              }}
              value={dataUser.role}
              renderInput={(params) => <TextField {...params} label="Rol" />}
            />
          )}
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
            {dataToEdit ? "Editar Usuario" : "Agregar Usuario"}
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

export default UpdateUser;
