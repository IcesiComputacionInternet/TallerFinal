import { useEffect, useState } from "react";
import axios from "axios";
import AdminNavbar from "../../components/AdminNavbar";

const baseUrl = "http://localhost:8091";

function Orders (){

  const [orders, setOrders] = useState([]);

  useEffect(() => {
    async function getData() {

      const resultOrders = await getOrders();
      setOrders(resultOrders);
    }

    getData();
  }, []);

  return (
    <>
     <AdminNavbar />
     <br />
    {orders.length > 0 ?(
      <div className="container">
        <p className="h4">Órdenes registradas</p>
       <br />
       <div className="table-responsive">
        <table className="table table-striped-columns" style={{tableLayout:"fixed"}}>
            <thead>
                <tr>
                <th scope="col">ID</th>
                <th scope="col">Email del usuario</th>
                <th scope="col">Celular del usuario</th>
                <th scope="col">Total</th>
                <th scope="col">Estado</th>
                <th scope="col">Items</th>
                </tr>
            </thead>
            <tbody>
            {orders.map((order, index) => (
                <tr key={index}>
                  <td>{order.orderId}</td>
                  <td>{order.userEmail}</td>
                  <td>{order.userPhoneNumber}</td>
                  <td>${order.total}</td>
                  <td>{order.status}</td>
                  <td>
                    {order.items.map((item) => item.name).join(", ")}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
       </div>
      </div>
      ) : (
        <div className="container">
          <p className="h4">No hay ordenes registradas</p>
        </div>
      )} 
    </>
  );
      
}

async function getOrders(){

  const orders = await axios.get(
    baseUrl+"/orders",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )
  return orders.data;
}

export default Orders;


