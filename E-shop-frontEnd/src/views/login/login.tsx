import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import UtilServices from "../../services/utilServices";
import LoginForm from "./loginForm";
import Header from "../utils/header";
import Footer from "../utils/footer";
import "./login.css";

const Login = () => {
  UtilServices.setTitlePage("E-Shop | Login");

  return (
    <div className="main">
      <Header />
      <div className="container">
        <div className="container-left">
          <Typography
            variant="h2"
            component="h2"
            sx={{
              textAlign: "center",
              fontWeight: "bold",
              marginBottom: "1rem",
            }}
          >
            E-Shop
          </Typography>
          <Typography
            variant="h6"
            component="h6"
            sx={{
              textAlign: "center",
              fontWeight: "bold",
              marginBottom: "1rem",
            }}
          >
            Inicia sesión para continuar
          </Typography>
        </div>
        <div className="container-right">
          <LoginForm />
          <Link
            to="/sing_up"
            style={{ fontFamily: "sans-serif", color: "gray" }}
          >
            ¿No tienes una cuenta? Crea una aquí
          </Link>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
