import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import ItemRow from "./itemRow";
import { v4 as uuidv4 } from "uuid";

interface IProps {
  itemsOrder: any;
  setItemsOrder: any;
  setOrderActive: any;
  addOrder: any;
}

const style = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "50%",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const BasicModal: React.FC<IProps> = ({
  itemsOrder,
  setItemsOrder,
  setOrderActive,
  addOrder,
}) => {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const totalPrice = () => {
    let total = 0;
    itemsOrder.map((el: any) => {
      total = el.price + total;
    });
    return total;
  };

  const handleSetItemsOrder = () => {
    setItemsOrder([]);
    setOrderActive(false);
    handleClose();
  };

  const handleAddOrder = () => {
    let user: string | null = localStorage.getItem("username");
    user = user ? user.replace(/['"]+/g, "") : null;
    const orderToAdd = {
      orderId: uuidv4(),
      userEmail: user,
      status: "Pendiente",
      total: totalPrice(),
      items: itemsOrder.map((item: any) => item.name),
    };

    addOrder(orderToAdd);
    setItemsOrder([]);
    setOrderActive(false);
    handleClose();
    handleClose();
  };

  return (
    <div>
      <Button
        variant="contained"
        sx={{
          mt: 3,
          mb: 5,
          bgcolor: "#D3D3D3",
          color: "black",
          position: "fixed",
          bottom: "50px",
          right: "50px",
          "&:hover": {
            bgcolor: "black",
            color: "white",
            transition: "0.5s",
          },
        }}
        onClick={handleOpen}
      >
        Ver Orden
      </Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Orden
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            Su orden tiene los siguienten CD's:
          </Typography>
          {itemsOrder.map((el: any) => (
            <ItemRow key={el.name} el={el} />
          ))}
          <Typography mb="10px">
            <b>Total: </b>
            {totalPrice()}
          </Typography>
          <div style={{ display: "flex", justifyContent: "space-around" }}>
            <Button variant="contained" onClick={() => handleSetItemsOrder()}>
              Cancelar Orden
            </Button>
            <Button variant="contained" onClick={handleClose}>
              Seguir Comprando
            </Button>
            <Button variant="contained" onClick={() => handleAddOrder()}>
              Comprar ahora
            </Button>
          </div>
        </Box>
      </Modal>
    </div>
  );
};

export default BasicModal;
