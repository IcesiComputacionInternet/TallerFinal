import React, {useState, useEffect} from "react";
import axios from "axios";
import {NavigateFunction,useNavigate} from "react-router-dom";
import Logout from "./Logout";
import Navbar from "./Navbar";

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
            <h2>My orders</h2>
          </div>
    
          <div className="m-3">
            {orders.length === 0 ? (
              <h6>No orders made</h6>
            ) : (
              orders.map((order) => (
                <div key={order.orderId} className="card m-3">
                  <div className="card-body">
                    <h5 className="card-title">{order.userEmail}</h5>
                    <h6 className="card-subtitle mb-2 text-body-secondary">
                       {order.userPhoneNumber}
                    </h6>
                    <h6 className="card-subtitle mb-2 text-body-secondary">
                       {order.status}
                    </h6>
                    <h6 className="card-subtitle mb-2 text-body-secondary">
                      {order.total}
                    </h6>
                    <div>
                        {order.items.length === 0 ? (
                            <h6>No items added</h6>
                            ) : (
                            order.items.map((item) => (
                                <div key={item.name} >
                                    <h5>{item.name}</h5>
                                    <h5>{item.price}</h5>
                                </div>
                         )))}
                    </div>
                  {(role==='ADMIN' || role==='SHOP') &&
                    <div>
                      <button
                      onClick={() => changeOrderStatus(order.orderId, "SHIPPED")}
                      disabled={order.status === "SHIPPED" || order.status === "RECEIVED"}
                      >
                      Ship Order
                      </button>
                      <button
                      onClick={() => changeOrderStatus(order.orderId, "RECEIVED")}
                      disabled={order.status === "RECEIVED"}
                      >
                      Order Received
                      </button>
                    </div>
                    }
                  </div>
                </div>
              ))
            )}
          </div>
          
        </div>
      );
}
export default Orders;