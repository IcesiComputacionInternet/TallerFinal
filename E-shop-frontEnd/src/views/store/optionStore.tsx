import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

const OptionsStore = () => {
  const navigate = useNavigate();

  const handleCrudItems = () => {
    navigate("/crud_items", { replace: true });
  };

  return (
    <>
      <div className="container-options">
        <div className="container-options-top">
          <Button
            onClick={handleCrudItems}
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
            Gestionar CD's
          </Button>
        </div>
      </div>
    </>
  );
};

export default OptionsStore;
