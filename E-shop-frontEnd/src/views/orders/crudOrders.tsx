import React from "react";
import TableOrder from "./tableOrders";
import Header from "../utils/header";
import Footer from "../utils/footer";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Typography } from "@mui/material";
import ModalAlert from "../utils/modalAlert";
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
  return (
    <>
      <Header />
      <TableOrder data={orders} setDataToEdit={setDataToEdit} />
      <Footer />
    </>
  );
};

export default CrudOrders;
