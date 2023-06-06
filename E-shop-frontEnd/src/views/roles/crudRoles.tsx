import { useState, useEffect } from "react";
import RoleServices from "../../services/roleServices";
import FormRole from "./formRole";
import TableRoles from "./tableRoles";
import Footer from "../utils/footer";
import Header from "../utils/header";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import ModalAlert from "../utils/modalAlert";

const CrudRoles = () => {
  const [roles, setRoles] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    try {
      RoleServices.getRoles().then((res) => {
        setRoles(res.data);
      });
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se han cargado los datos correctamente"
      );
    }
  }, []);

  const createData = (data: any) => {
    try {
      RoleServices.create(data).then((res) => {
        console.log(res);
        setRoles((prevRoles) => [...prevRoles, res.data]);
      });
      ModalAlert.ModalAlertSuccess("Exito", "Rol creado correctamente");
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se ha podido crear el rol correctamente, puede que el nombre ya exista"
      );
    }
  };

  const updateData = (data: any) => {
    try {
      let updatedRole: any = null;
      let newRoles = roles.map((el: any) => {
        if (el.roleName === data.roleName) {
          updatedRole = data;
          return data;
        }
        return el;
      });

      RoleServices.update(updatedRole).then((_res) => {
        setRoles(newRoles);
      });
      ModalAlert.ModalAlertSuccess("Exito", "Rol actualizado");
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se ha actualizado el rol correctamente"
      );
    }
  };

  const deleteData = async (data: any) => {
    try {
      await RoleServices.deleteRole(data);
      const filteredRoles = roles.filter((el: any) => el.roleName !== data);
      setRoles(filteredRoles);
      ModalAlert.ModalAlertSuccess("Exito", "Rol borrado correctamente");
    } catch (error) {
      ModalAlert.ModalAlertError(
        "Error",
        "No se ha podido borrar el Rol, puede que este instanciado en otro objeto"
      );
    }
  };

  return (
    <>
      <Header />
      <div className="container-add-role">
        <FormRole
          createData={createData}
          updateData={updateData}
          dataToEdit={dataToEdit}
          setDataToEdit={setDataToEdit}
        />
        <TableRoles
          data={roles}
          setDataToEdit={setDataToEdit}
          deleteData={deleteData}
        />
      </div>
      <Link to="/" style={{ textAlign: "center" }}>
        <Typography>Volver</Typography>
      </Link>
      <Footer />
    </>
  );
};

export default CrudRoles;
