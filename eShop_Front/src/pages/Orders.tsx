import React, { useEffect, useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import Table from "../components/Table";
import { ResponseOrderDTO, ResponseItemDTO } from "../interfaces/Order";
import "../styles/orders.css";
import axios from "axios";
import {
  Button,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";

const baseUrl = "http://localhost:8080";

const token = localStorage.getItem("jwt");

const Orders = () => {
  const [orderId, setOrderId] = useState("");
  const [status, setStatus] = useState("");

  const handleChange = (event: SelectChangeEvent) => {
    setStatus(event.target.value as string);
  };

  const handleOrderToUpdate = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOrderId(event.target.value as string);
  };

  const handleChangeStatus = async (event: React.FormEvent) => {
    event.preventDefault();
    console.log(orderId, status)
  
    try {
      const response = await axios.patch(baseUrl+'/api/orders/update/status', {
        orderId,
        status,
      }, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      window.location.reload();
    } catch (error) {
      alert("Se presentó un error")
      console.error(error);
    }
  };

  const handleLogout = () => {
    localStorage.clear;
    alert("La sesión ha sido cerrada correctamente");
    navigate("/");
  };

  const navigate: NavigateFunction = useNavigate();

  const [orders, setOrders] = React.useState<ResponseOrderDTO[]>([]);

  const columns = [
    { heading: "Order Number", value: "orderId" },
    { heading: "Client", value: "user" },
    { heading: "Status", value: "status" },
    { heading: "Amount", value: "total" },
    {
      heading: "Products",
      value: "items",
      render: (items: ResponseItemDTO[]) =>
        items.map((item: ResponseItemDTO) => item.name).join(", "),
    },
  ];

  useEffect(() => {
    axios
      .get(baseUrl + "/api/orders/get/all", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setOrders(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <div className="Table">
      <h1>Orders</h1>
      <Table data={orders} columns={columns}></Table>
      <Button
        sx={{ marginRight: 10 }}
        variant="contained"
        color="error"
        className="LogoutBtn"
        onClick={handleLogout}
      >
        Log Out
      </Button>
      <Button
        variant="contained"
        color="primary"
        className="ChangeStatusBtn"
        onClick={handleChangeStatus}
      >
        Change Status
      </Button>
      <label htmlFor="orderId" style={{ marginLeft: "10px" }}>
        Ingrese el Order ID:
      </label>
      <input type="text" id="orderId" style={{ marginLeft: "10px" }} onChange={handleOrderToUpdate}/>
      <label htmlFor="orderId" style={{ marginLeft: "10px" }}>
        Escoja el nuevo estado:
      </label>
      <Select
        sx={{ maxHeight: 30, marginLeft: 1 }}
        labelId="demo-simple-select-label"
        id="demo-simple-select"
        value={status}
        onChange={handleChange}
      >
        <MenuItem value={"PENDING"}>Pending</MenuItem>
        <MenuItem value={"PAID"}>Paid</MenuItem>
        <MenuItem value={"CANCELLED"}>Cancelled</MenuItem>
        <MenuItem value={"DELIVERED"}>Delivered</MenuItem>
        <MenuItem value={"DONE"}>Done</MenuItem>
      </Select>
    </div>
  );
};

export default Orders;


