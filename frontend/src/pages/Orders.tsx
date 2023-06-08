import React, { useEffect, useState } from "react";
import axios from "axios";
import storeItems from "../testData/items.json"
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
        <div className="login-container">
            <div className="container mt-4">
                <div className="row justify-content-center">
                    <h1>
                        Orders
                    </h1>
                    {storeItems.length > 0 ? (
                        <div className="table-responsive">
                            <table className="table table-bordered mx-auto mt-3 table-striped" style={{ width: "80%" }}>
                                <thead >
                                <tr>
                                    <th>User</th>
                                    <th>Status</th>
                                    <th>Items</th>
                                    <th>Address</th>
                                    <th>Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                {storeItems && storeItems.map((item: any, index) => (
                                    <tr key={index}>
                                        <td>{item.id}</td>
                                        <td>
                                            <select className="form-select" style={{width:"50%"}}>
                                                <option value="IN_PROCESS">IN PROCESS</option>
                                                <option value="SENT">SENT</option>
                                                <option value="DELIVERED">DELIVERED</option>
                                            </select>
                                        </td>
                                        {/*<td>{item.name}</td>*/}
                                        {/*<td>{order.items}</td>*/}
                                        {/*<td>{order.total}</td>*/}

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