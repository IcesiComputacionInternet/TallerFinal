import { Typography } from "@mui/material";
import AddRole from "./addRole";
import Footer from "../utils/footer";
import Header from "../utils/header";

const CrudRoles = () => {
  return (
    <>
      <Header />
      <div className="container-add-role">
        <Typography variant="h2" component="h2">
          Agregar Rol
        </Typography>
        <AddRole />
      </div>
      <Footer />
    </>
  );
};

export default CrudRoles;
