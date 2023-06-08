import React, {useState, useEffect} from "react";
import axios from "axios";
import {NavigateFunction,useNavigate} from "react-router-dom";
import Logout from "./Logout";

const baseUrl="http://localhost:8091";


function Orders(){
    
    const [orders, setOrders] = useState([]);

    useEffect(() => {
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

    
    return (
        <div>
          <div
            className="p-3 navbar bg-body-tertiary"
            style={{ backgroundColor: "#e3f2fd" }}
          >
            <div className="col-1">
              <h4>Orders</h4>
            </div>
            <div className="col-1">
              <Logout />
            </div>
          </div>
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
                  
                  </div>
                </div>
              ))
            )}
          </div>
        </div>
      );
}
export default Orders;