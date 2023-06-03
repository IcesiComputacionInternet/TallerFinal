import axios from "axios";
import authHeader from "./authHeader";

const URL_API = "http://localhost:8080/api/orders/";

interface OrderProps {
  userEmail: string;
  status: string;
  total: number;
  items: string[];
}
const create = (newOrder: OrderProps) => {
    return axios.post(`${URL_API}create`, newOrder , {
        headers: authHeader()
    })
};

const update = (order:OrderProps)  => {
    return axios.put(`${URL_API}update`, order, {
        headers: authHeader()
    })
}

const deleteOrder = (orderName:string) => {
    return axios.delete(`${URL_API}delete`,{
        data: orderName, 
        headers: authHeader()
    })
}

const pay = (orderName:string) => {
    return axios.patch(`${URL_API}pay`, orderName,{
        headers: authHeader()
    })
}

const deliver = (orderName:string) => {
    return axios.patch(`${URL_API}deliver`, orderName,{
        headers: authHeader()
    })
}

const receive = (orderName:string) => {
    return axios.patch(`${URL_API}receive`, orderName,{
        headers: authHeader()
    })
}

const getOrders = () =>{
    return axios.get(`${URL_API}all`,{
        headers: authHeader()
    })
}

const orderServices={
    create, 
    update,
    deleteOrder,
    pay,
    receive,
    deliver,
    getOrders
}

export default orderServices