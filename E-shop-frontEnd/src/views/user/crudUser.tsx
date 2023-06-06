import { useState, useEffect } from "react";
import UserServices from "../../services/userServices";
import Header from "../utils/header";
import Footer from "../utils/footer";
import TableUser from "./tableUser";
import UpdateUser from "./updateUser";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import RoleServices from "../../services/roleServices";
import ModalAlert from "../utils/modalAlert";

const CrudUser = () => {
  const [users, setUsers] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);
  const [roles, setRoles] = useState<string[]>([]);

  useEffect(() => {
    try {
      UserServices.getUsers().then((res) => {
        setUsers(res.data);
      });
      RoleServices.getRoles().then((res) => {
        const newRoles = res.data.map((element: any) => element.roleName);
        setRoles((prevRoles) => [...prevRoles, ...newRoles]);
      });
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se han cargado los datos correctamente"
      );
    }
  }, []);

  const updateData = (data: any) => {
    try {
      let updateUser: any = null;
      let newUser = users.map((el: any) => {
        if (el.email === data.email) {
          updateUser = data;
          return data;
        }
        return el;
      });
      updateUser = { ...updateUser, password: "empty" };
      console.log(updateUser);
      UserServices.update(updateUser).then((_res) => {
        setUsers(newUser);
      });
      ModalAlert.ModalAlertSuccess("Exito", "Usuario actualizado");
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se ha actualizado el usuario correctamente, puede que el telefono ya exista"
      );
    }
  };

  const deleteData = async (data: any) => {
    try {
      await UserServices.deleteUser(data);
      const filteredUser = users.filter((el: any) => el.email !== data);
      setUsers(filteredUser);
      ModalAlert.ModalAlertSuccess("Exito", "Usuario eliminado");
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se ha eliminado el usuario correctamente, puede que este instanciado en otro objeto"
      );
    }
  };

  return (
    <>
      <Header />
      {dataToEdit && (
        <UpdateUser
          dataRoles={roles}
          updateData={updateData}
          dataToEdit={dataToEdit}
          setDataToEdit={setDataToEdit}
        />
      )}
      <TableUser
        data={users}
        setDataToEdit={setDataToEdit}
        deleteData={deleteData}
      />
      <Link to="/" style={{ textAlign: "center" }}>
        <Typography>Volver</Typography>
      </Link>
      <Footer />
    </>
  );
};

export default CrudUser;
