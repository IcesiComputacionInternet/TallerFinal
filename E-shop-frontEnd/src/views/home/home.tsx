import OptionsAdmin from "../admin/optionsAdmin";
import OptionsStore from "../store/optionStore";
import Header from "../utils/header";
import Footer from "../utils/footer";
import ViewUser from "./viewUser";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();
  const logout = () => {
    localStorage.clear();
    navigate("/login", { replace: true });
  };
  var role: string | null = localStorage.getItem("role");

  return (
    <>
      <Header />
      {role === '"ADMIN"' ? (
        <>
          <OptionsAdmin />
        </>
      ) : (
        <></>
      )}
      {role === '"Store"' ? (
        <>
          <OptionsStore></OptionsStore>
        </>
      ) : (
        <></>
      )}
      <ViewUser />
      <div style={{ width: "30%", display: "flex", margin: "auto" }}>
        <Button
          onClick={logout}
          fullWidth
          variant="contained"
          sx={{
            mt: 0,
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
          Log out
        </Button>
      </div>
      <Footer />
    </>
  );
};

export default Home;
