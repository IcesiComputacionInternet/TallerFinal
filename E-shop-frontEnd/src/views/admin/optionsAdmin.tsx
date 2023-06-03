import { Button } from "@mui/material";
import "./optionsAdm.css";
import { Navigate, useNavigate } from "react-router-dom";

const OptionsAdmin = () => {
  const navigate = useNavigate();

  const handleCreateUser = () => {
    navigate("/add_user_admin", { replace: true });
  };

  return (
    <>
      <div className="container-options">
        <div className="container-options-top">
          <Button
            onClick={handleCreateUser}
            variant="contained"
            sx={{
              width: "20%",
              height: "60px",
              mt: 3,
              mb: 2,
              bgcolor: "#D3D3D3",
              color: "black",
              "&:hover": {
                bgcolor: "black",
                color: "white",
                transition: "0.5s",
              },
            }}
          >
            Crear Usuarios
          </Button>
          <Button
            variant="contained"
            sx={{
              width: "20%",
              mt: 3,
              mb: 2,
              bgcolor: "#D3D3D3",
              color: "black",
              "&:hover": {
                bgcolor: "black",
                color: "white",
                transition: "0.5s",
              },
            }}
          >
            Gestionar Usuarios
          </Button>
          <Button
            variant="contained"
            sx={{
              width: "20%",
              mt: 3,
              mb: 2,
              bgcolor: "#D3D3D3",
              color: "black",
              "&:hover": {
                bgcolor: "black",
                color: "white",
                transition: "0.5s",
              },
            }}
          >
            Gestionar Roles
          </Button>
          <Button
            variant="contained"
            sx={{
              width: "20%",
              mt: 3,
              mb: 2,
              bgcolor: "#D3D3D3",
              color: "black",
              "&:hover": {
                bgcolor: "black",
                color: "white",
                transition: "0.5s",
              },
            }}
          >
            Gestionar items
          </Button>
        </div>
        <div className="container-options-bottom">
          <Button
            variant="contained"
            sx={{
              width: "20%",
              height: "60px",
              mt: 3,
              mb: 2,
              bgcolor: "#D3D3D3",
              color: "black",
              "&:hover": {
                bgcolor: "black",
                color: "white",
                transition: "0.5s",
              },
            }}
          >
            Gestionar categorias
          </Button>
        </div>
      </div>
    </>
  );
};

export default OptionsAdmin;
