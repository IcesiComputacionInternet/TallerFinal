import TableOrder from "./tableOrders";
import Header from "../utils/header";
import Footer from "../utils/footer";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import ModalAlert from "../utils/modalAlert";
import UpdateOrders from "./updateOrders";
import OrderServices from "../../services/orderServices";

const CrudOrders = () => {
  const [orders, setOrders] = useState<string[]>([]);
  const [dataToEdit, setDataToEdit] = useState<any>(null);

  useEffect(() => {
    try {
      OrderServices.getOrders().then((res) => {
        setOrders(res.data);
        console.log(res.data);
      });
    } catch (error) {
      ModalAlert.ModalAlertError("Error", "Error al cargar las ordenes");
    }
  }, []);

  const updateData = async (data: any) => {
    try {
      let updateOrder: any = null;
      let newOrder = orders.map((el: any) => {
        if (el.id === data.id) {
          updateOrder = data;
          return data;
        }
        return el;
      });

      updateOrder = { ...updateOrder, id: data.id };

      let updatePromise: Promise<any> | null = null;

      if (data.status === "PAID") {
        updatePromise = OrderServices.pay(data.id);
      } else if (data.status === "SENT") {
        updatePromise = OrderServices.deliver(data.id);
      } else if (data.status === "RECEIVED") {
        updatePromise = OrderServices.receive(data.id);
      }

      if (updatePromise) {
        await updatePromise;
        setOrders(newOrder);
      }

      ModalAlert.ModalAlertSuccess("Exito", "Orden actualizada");
    } catch (error) {
      console.log(error);

      ModalAlert.ModalAlertError("Error", "Error al actualizar la orden");
    }
  };

  return (
    <>
      <Header />
      <Typography variant="h4" align="center" sx={{ mt: 5 }}>
        Gestionar Ordenes
      </Typography>
      {dataToEdit && (
        <UpdateOrders
          updateData={updateData}
          dataToEdit={dataToEdit}
          setDataToEdit={setDataToEdit}
        />
      )}
      <TableOrder data={orders} setDataToEdit={setDataToEdit} owns={true} />
      <Link to="/" style={{ textAlign: "center" }}>
        <Typography>Volver</Typography>
      </Link>
      <Footer />
    </>
  );
};

export default CrudOrders;
