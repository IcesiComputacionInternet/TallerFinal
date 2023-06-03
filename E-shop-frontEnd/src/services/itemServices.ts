import axios from "axios";
import authHeader from "./authHeader";

const URL_API = "http://localhost:8080/api/items/";

interface ItemProps{
    description: string, 
    name: string , 
    price: number,
    imageUrl: string, 
    categoryId: string, 
    orderId: string 
}

const createItem = (item: ItemProps) =>{
    return axios.post(`${URL_API}create`,item, {
        headers: authHeader()
    })
}

const deleteItem = (itemName: string) =>{
    return axios.delete(`${URL_API}delete`, {
        data: itemName,
        headers: authHeader()
    })
}

const updateItem = (item: ItemProps) =>{
    return axios.put(`${URL_API}update`,item, {
        headers: authHeader()
    })
}

const findItem = (itemName: string) =>{
    return axios.get(`${URL_API}/find/${itemName}`, {
        headers: authHeader()
    })
}

const getItems= () =>{
    return axios.get(`${URL_API}all`, {
        headers: authHeader()
    })
}

const itemServices = {
    createItem,
    deleteItem,
    updateItem,
    findItem,
    getItems
}

export default itemServices