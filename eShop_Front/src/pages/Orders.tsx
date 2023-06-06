import React, { useEffect, useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import Table from "../components/Table";
import { Order } from "../interfaces/Order";
import "../styles/orders.css";

const baseUrl = "http://localhost:8080";

const Orders = () => {

  const [orders, setOrders] = React.useState<Order[]>([]);

  const columns = [
    { heading: "Order Number", value: "orderNumber" },
    { heading: "Client", value: "clientName" },
    { heading: "Products", value: "products" },
    { heading: "Amount", value: "amount" },
  ];

  return (
    <div className="Table">
      <h1>Orders</h1>
      <Table data={orders} column={columns}></Table>
      <button className="LogoutBtn">Log Out</button>
    </div>
  );
};

export default Orders;
