import UtilServices from "../../services/utilServices";
import { Link } from "react-router-dom";
import Header from "../utils/header";
import Footer from "../utils/footer";
import UserForm from "./userForm";

interface props {
  rolAdmin: Boolean;
}

const CreateUser: React.FC<props> = ({ rolAdmin }) => {
  UtilServices.setTitlePage("E-Shop | Sing Up");
  return (
    <div className="main">
      <Header />
      <div className="container">
        <UserForm rolAdmin={rolAdmin} />
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
        ¿Ya tienes una cuenta? Inicia sesión aquí
      </Link>
      <Footer />
    </div>
  );
};

export default CreateUser;
