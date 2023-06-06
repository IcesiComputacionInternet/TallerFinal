import UtilServices from "../../services/utilServices";
import RoleServices from "../../services/roleServices";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Header from "../utils/header";
import Footer from "../utils/footer";
import UserForm from "./userForm";

interface props {
  rolAdmin: Boolean;
}

const CreateUser: React.FC<props> = ({ rolAdmin }) => {
  const [roles, setRoles] = useState<string[]>([]);

  useEffect(() => {
    if (rolAdmin) {
      RoleServices.getRoles().then((res) => {
        const newRoles = res.data.map((element: any) => element.roleName);
        setRoles((prevRoles) => [...prevRoles, ...newRoles]);
      });
    }
  }, []);

  UtilServices.setTitlePage("E-Shop | Sing Up");
  return (
    <div className="main">
      <Header />
      <div className="container">
        <UserForm rolAdmin={rolAdmin} roles={roles} />
      </div>
      <Link
        to="/login"
        style={{
          fontFamily: "sans-serif",
          color: "gray",
          textAlign: "center",
          width: "100%",
          display: "block",
          marginBottom: "1rem",
        }}
      >
        {rolAdmin ? "Volver" : "¿Ya tienes una cuenta? Inicia sesión aquí"}
      </Link>
      <Footer />
    </div>
  );
};

export default CreateUser;
