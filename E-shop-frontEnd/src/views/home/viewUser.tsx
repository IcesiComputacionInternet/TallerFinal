import { useState, useEffect } from "react";
import ItemServices from "../../services/itemServices";
import CardItem from "./card";
import { Typography, Button } from "@mui/material";
import BasicModal from "./modalOrder";
import OrderServices from "../../services/orderServices";
import ModalAlert from "../utils/modalAlert";
import { useNavigate } from "react-router-dom";

const ViewUser = () => {
  const [items, setItems] = useState<any[]>([]);
  const [itemsOrder, setItemsOrder] = useState<any[]>([]);
  const [orderActive, setOrderActive] = useState<Boolean>(false);
  const navigate = useNavigate();

  useEffect(() => {
    ItemServices.getItems().then((res) => {
      setItems(res.data);
    });
  }, []);

  const handleViewOrder = () => {
    navigate("/view_orders_user", { replace: true });
  };

  const handleAddItemsOrder = (item: any) => {
    setItemsOrder((prevItems) => [...prevItems, item]);
    console.log(item);
  };

  const addOrder = async (data: any) => {
    console.log(data);
    try {
      await OrderServices.create(data);
      ModalAlert.ModalAlertSuccess("Exito", "Orden creada correctamente");
    } catch (error) {
      console.log(error);
      ModalAlert.ModalAlertError("Error", "Error al crear la orden");
    }
  };

  return (
    <>
      <Typography
        variant="h4"
        component="h4"
        textAlign="center"
        marginBottom="20px"
        marginTop="20px"
      >
        Comprar CD's
      </Typography>
      <div
        className="container-cards"
        style={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "space-between",
          gap: "3rem",
          width: "80%",
          margin: "auto",
          marginBottom: "5rem",
        }}
      >
        {items.map((el: any) => (
          <CardItem
            key={el.name}
            el={el}
            setOrderActive={setOrderActive}
            handleAddItemsOrder={handleAddItemsOrder}
            orderActive={orderActive}
          />
        ))}
      </div>

      {orderActive && (
        <BasicModal
          itemsOrder={itemsOrder}
          setItemsOrder={setItemsOrder}
          setOrderActive={setOrderActive}
          addOrder={addOrder}
        />
      )}
      <div style={{ width: "30%", display: "flex", margin: "auto" }}>
        <Button
          onClick={handleViewOrder}
          fullWidth
          variant="contained"
          sx={{
            mt: 3,
            mb: 5,
            bgcolor: "#D3D3D3",
            color: "black",
            "&:hover": {
              bgcolor: "black",
              color: "white",
              transition: "0.5s",
            },
          }}
        >
          Ver Ordenes activas
        </Button>
      </div>
    </>
  );
};

export default ViewUser;
