import { useState, useEffect } from "react";
import { Typography } from "@mui/material";
import RoleServices from "../../services/roleServices";
import AddRole from "./addRole";
import TableRoles from "./tableRoles";
import Footer from "../utils/footer";
import Header from "../utils/header";

const CrudRoles = () => {
  const [roles, setRoles] = useState([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    RoleServices.getRoles().then((res) => {
      setRoles(res.data);
    });
  }, []);

  const deleteData = (name: string) => {};

  return (
    <>
      <Header />
      <div className="container-add-role">
        <Typography
          variant="h4"
          component="h4"
          sx={{ m: 3, textAlign: "center" }}
        >
          Agregar Rol
        </Typography>
        <AddRole />
        <TableRoles
          data={roles}
          setDataToEdit={setDataToEdit}
          deleteData={deleteData}
        />
      </div>
      <Footer />
    </>
  );
};

export default CrudRoles;
