import { useState, useEffect } from "react";
import RoleServices from "../../services/roleServices";
import FormRole from "./formRole";
import TableRoles from "./tableRoles";
import Footer from "../utils/footer";
import Header from "../utils/header";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";

const CrudRoles = () => {
  const [roles, setRoles] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    RoleServices.getRoles().then((res) => {
      setRoles(res.data);
    });
  }, []);

  const createData = (data: any) => {
    RoleServices.create(data).then((res) => {
      console.log(res);
      setRoles((prevRoles) => [...prevRoles, res.data]);
    });
  };

  const updateData = (data: any) => {
    let updatedRole: any = null;
    let newRoles = roles.map((el: any) => {
      if (el.roleName === data.roleName) {
        updatedRole = data;
        return data;
      }
      return el;
    });
    setRoles(newRoles);

    RoleServices.update(updatedRole).then((res) => {
      console.log(res);
      console.log(updatedRole); // Valor actualizado
    });
  };

  const deleteData = async (data: any) => {
    try {
      await RoleServices.deleteRole(data);
      const filteredRoles = roles.filter((el: any) => el.roleName !== data);
      setRoles(filteredRoles);
    } catch (error) {
      console.error(error);
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
