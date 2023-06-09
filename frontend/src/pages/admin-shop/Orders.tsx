import React, {useState, useEffect} from "react";
import axios from "axios";
import {NavigateFunction,useNavigate} from "react-router-dom";
import Navbar from "../../components/Navbar";

const baseUrl="http://localhost:8091";


function Orders(){
    
    const [orders, setOrders] = useState([]);
    const [role, setRole] = useState("");

    useEffect(() => {
      const checkRole= async () => {
        
        var token=localStorage.getItem("jwt");
        if (token) {

            const user = localStorage.getItem("currentRole");
            
            if(user){
                setRole(user)
            }
        }
        };
        checkRole();

    const fetchItems = async () => {
        var token=localStorage.getItem("jwt");
        const response = await axios.get(
            baseUrl + "/orders",
            { headers: { 
                "Access-Control-Allow-Origin":baseUrl,
                "MediaType":"application/json",
                Authorization: `Bearer ${token}` 
            } }
            );
        const responseData = response.data;
        setOrders(responseData);
    };

    fetchItems();
    }, []);


    const changeOrderStatus = async (cOrderId, newStatus) => {
      
          var token = localStorage.getItem("jwt");
          const response = await axios.put(
            baseUrl+"/orders/"+`${cOrderId}`,
            {
                orderId:cOrderId,
                status: newStatus },
            {
              headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "MediaType": "application/json",
                Authorization: `Bearer ${token}`
              }
            }
          );
          window.location.reload();
      
      };
    
    return (
        <div>
          <Navbar/>

          <div className="m-3">
            <h2>Mis órdenes</h2>
          </div>
    
          <div className="m-3">
            {orders.length === 0 ? (
              <h6>Nunca es tarde para hacer una compra ;)</h6>
            ) : (
              <div className="container">
                <div className="row">

              {orders.map((order) => (
                <div key={order.orderId} className=" col-md-6" >
                  <div className="card m-3">
                  <div className="card-body " >
                    <h5 className="card-title">ID Orden: {order.orderId}</h5>
                    
                    {order.status==="PENDING" &&
                      <h6 className="p-3 mb-2 bg-danger text-white">
                       Pendiente
                      </h6>
                    }
                    {order.status==="SHIPPED" &&
                      <h6 className="p-3 mb-2 bg-warning text-dark">
                          Enviado
                      </h6>
                    }
                    {order.status==="RECEIVED" &&
                      <h6 className="p-3 mb-2 bg-success text-white">
                         Recibido
                     </h6>
                    }
                    
                  
                    <div className="row">
                                                          
                              <table className="table table-hover">
                                  <thead>
                                      <tr>
                                          <th>Producto</th>
                                          <td>   </td>
                                          <th className="text-center">Precio</th>
                                      </tr>
                                  </thead>

                                  
                                  <tbody>
                                 
                                    {order.items.length === 0 ? (
                                        <h6>No se han agregado productos</h6>
                                        ) : (
                                        order.items.map((item) => (
                                            
                                                <tr key={item.name}>
                                                    <td className="col-md-5"><em>{item.name}</em></td>
                                                    <td>   </td>
                                                    <td className="col-md-1 text-center">${item.price}</td>
                                                </tr>
                                            
                                    )))}
                                                                                                         
                                      <tr>
                                          <td>   </td>
                                          <td className="col-md-1 text-center"><strong>Total: </strong></td>
                                          <td className="col-md-1 text-center text-danger"><strong>$ {order.total}</strong></td>
                                      </tr>
                                  </tbody>
                              </table>
                          
                      </div>

                  
                  {(role==='ADMIN' || role==='SHOP') &&
                    <div className="text-center">
                      <button
                      className="btn btn-outline-warning me-3"
                      onClick={() => changeOrderStatus(order.orderId, "SHIPPED")}
                      disabled={order.status === "SHIPPED" || order.status === "RECEIVED"}
                      >
                      <strong>Enviar orden</strong>
                      </button>
                      
                      <button
                      className="btn btn-outline-success"
                      onClick={() => changeOrderStatus(order.orderId, "RECEIVED")}
                      disabled={order.status === "RECEIVED"}
                      >
                      <strong>Orden recibida</strong>
                      </button>
                    </div>
                    }
                  </div>
                  </div>
                </div>
                
              ))}
               </div>
                </div>
            )}
          </div>
          
        </div>
      );
}
export default Orders;