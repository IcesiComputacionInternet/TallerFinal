import React, { useEffect, useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import Table from "../components/Table";
import { Order } from "../interfaces/Order";
import "../styles/orders.css";
import axios from "axios";

const baseUrl = "http://localhost:8080";

const token = localStorage.getItem("jwt");

const Orders = () => {

  const [orders, setOrders] = React.useState<Order[]>([]);

  const columns = [
    { heading: "Order Number", value: "orderId" },
    { heading: "Client", value: "user" },
    { heading: "Status", value: "status"},
    { heading: "Amount", value: "total" },
    { heading: "Products", value: "items" },
  ];

  useEffect(() => {
    axios.get(baseUrl+ '/api/orders/get/all', {
        headers: {
          'Authorization': `Bearer ${token}`,          
        }
    })
    .then(response => {
      setOrders(response.data)
      console.log(response.data);
    })
    .catch(error => {
      console.error(error);
    });
  },[])

  return (
    <div className="Table">
      <h1>Orders</h1>
      <Table data={orders} column={columns}></Table>
      <button className="LogoutBtn">Log Out</button>
    </div>
  );
};

export default Orders;
