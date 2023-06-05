import { useState, useEffect } from "react";
import UserServices from "../../services/userServices";
import Header from "../utils/header";
import Footer from "../utils/footer";
import TableUser from "./tableUser";
import UpdateUser from "./updateUser";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import RoleServices from "../../services/roleServices";

const CrudUser = () => {
  const [users, setUsers] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);
  const [roles, setRoles] = useState<string[]>([]);

  useEffect(() => {
    UserServices.getUsers().then((res) => {
      setUsers(res.data);
    });
    RoleServices.getRoles().then((res) => {
      const newRoles = res.data.map((element: any) => element.roleName);
      setRoles((prevRoles) => [...prevRoles, ...newRoles]);
    });
  }, []);

  const updateData = (data: any) => {
    console.log(data);
    let updatedUser: any = null;
    let newUser = roles.map((el: any) => {
      if (el.email === data.email) {
        updatedUser = data;
        return data;
      }
      return el;
    });

    UserServices.update(updatedUser).then((_res) => {
      setRoles(newUser);
    });
  };

  const deleteData = async (data: any) => {
    try {
      await UserServices.deleteUser(data);
      const filteredUser = users.filter((el: any) => el.roleName !== data);
      setRoles(filteredUser);
    } catch (error) {
      console.error(error);
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
