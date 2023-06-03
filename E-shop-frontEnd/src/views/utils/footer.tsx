import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import "./styles.css";

const Footer = () => {
  return (
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
  );
};

export default Footer;
