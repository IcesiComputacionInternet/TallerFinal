import axios from "axios";

const baseUrl = "http://localhost:8080";

export const postOrder = async (userId:string, items:string[]) => {
    const jwt = localStorage.getItem("jwt");
    const {data} = await axios.post(
        `${baseUrl}/purchaseOrders`,
        {
            eShopUserUUID: userId,
            items: items
        },
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            }
        }
    );
    return data;
}

export const getOrders = async () => {
    const jwt = localStorage.getItem("jwt");
    const {data} = await axios.get(
        `${baseUrl}/purchaseOrders/`,
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`,
            }
        }
    );
    return data;
}