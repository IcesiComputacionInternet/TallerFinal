import React, { useEffect, useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";

const baseUrl = "http://localhost:8080";

function Orders(){
    const [orders, setOrders] = useState([]);
    const navigation : NavigateFunction = useNavigate();


    // useEffect(() => {
    //     localStorage.setItem("logged_user", JSON.stringify(true));
    //     getAccountData();
    // }, []);

    // async function getAccountData() {
    //     const result = await userData();
    //     setAccounts(result);
    // }

    return (
        <div className="container">
            <div className="container mt-4">
                <div className="row justify-content-center">
                    <h1>
                        Orders
                    </h1>
                    {orders.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table table-bordered mx-auto mt-3 table-striped" style={{ width: "80%" }}>
                                <thead >
                                <tr>
                                    <th>User</th>
                                    <th>Status</th>
                                    <th>Total</th>
                                    <th>Items</th>
                                </tr>
                                </thead>
                                <tbody>
                                {orders && orders.map((order: any, index) => (
                                    <tr key={index}>
                                        <td>{order.users}</td>
                                        <td>{order.status}</td>
                                        <td>{order.total}</td>
                                        <td>{order.items}</td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    ) : (
                        <h3>There are no orders</h3>
                    )}
                </div>
            </div>
        </div>
    );
}


// async function userData() {
//     const data = await axios.get(
//         baseUrl + "/users/getAccounts",
//         {
//             headers: {
//                 "Access-Control-Allow-Origin": baseUrl,
//                 "MediaType": "application/json",
//                 "Authorization": "Bearer " + localStorage.getItem('jwt')
//             }
//         }
//     );
//     console.log(data);
//     return data.data;
// }

export default Orders;