import { useState, useEffect } from "react";
import OrderServices from "../../services/orderServices";
import ModalAlert from "../utils/modalAlert";
import Header from "../utils/header";
import Footer from "../utils/footer";
import { Typography } from "@mui/material";
import TableOrder from "../orders/tableOrders";

const ViewOrders = () => {
  const [orders, setOrders] = useState<string[]>([]);

  useEffect(() => {
    let user: string = localStorage.getItem("username") || "";
    user = user.replace(/['"]+/g, "");
    try {
      OrderServices.getOrdersByUser(user).then((res) => {
        setOrders(res.data);
      });
    } catch (error) {
      console.log(error);
      ModalAlert.ModalAlertError("Error", "Error al cargar las ordenes");
    }
  }, []);

  return (
    <>
      <Header />
      <Typography variant="h4" align="center" sx={{ mt: 5 }}>
        Tus ordenes
      </Typography>
      <TableOrder data={orders} setDataToEdit={null} />
      <Footer />
    </>
  );
};

export default ViewOrders;
