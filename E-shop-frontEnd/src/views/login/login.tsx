import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import UtilServices from "../../services/utilServices";
import Logo from "../../assets/logo_transparent_min.png";
import LoginForm from "./loginForm";
import Swal from "sweetalert2";
import "./login.css";

const Login = () => {
  UtilServices.setTitlePage("E-Shop | Login");

  return (
    <div className="main">
      <div className="header">
        <img src={Logo} alt="Logo" className="logo" />
      </div>
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
        </div>
      </div>
      <footer className="footer">
        <Typography
          variant="subtitle1"
          color="black"
          align="center"
          className="credits"
        >
          {"Copyright © "}
          <Link
            style={{ textDecoration: "none", color: "inherit" }}
            to="https://github.com/GabrielSB19"
            className="links"
          >
            Gabriel Suarez -
          </Link>{" "}
          <Link
            style={{ textDecoration: "none", color: "inherit" }}
            to="https://github.com/GabrielSB19"
            className="links"
          >
            Camilo Campaz -
          </Link>{" "}
          <Link
            style={{
              textDecoration: "none",
              color: "inherit",
              marginRight: "5px",
            }}
            to="https://github.com/GabrielSB19"
            className="links"
          >
            Johan Ricardo
          </Link>{" "}
          {new Date().getFullYear()}
          {"."}
        </Typography>
      </footer>
    </div>
  );
};

export default Login;
