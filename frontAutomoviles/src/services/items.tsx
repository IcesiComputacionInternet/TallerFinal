import axios from "axios";

const baseUrl = "http://localhost:8080";

export const getItemsPage = async (page: number, filter: string) => {
    if(filter !== ""){
        const {data} = await axios.get(
            `${baseUrl}/items?page=${page}&filter=${filter}`,
            {
                headers: {
                    "Access-Control-Allow-Origin": baseUrl,
                    "Content-Type": "application/json"
                }
            }
        );
        return data;
    }else{
        const {data} = await axios.get(
            `${baseUrl}/items?page=${page}`,
            {
                headers: {
                    "Access-Control-Allow-Origin": baseUrl,
                    "Content-Type": "application/json"
                }
            }
        );
        return data;
    }
}

export const postItem = async (name:string, desc:string, price: number, image: string, category: string) => {
    const {data} = await axios.post(
        `${baseUrl}/items`,
        {
            name: name,
            description: desc,
            price: price,
            imageUrl: image,
            categoryUUID: category
        },
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("jwt")}`
            }
        }
    );
    return data;
}

export const patchItem = async (itemId:string, name:string, desc:string, price: number, image: string, category: string) => {
    const {data} = await axios.patch(
        `${baseUrl}/items/${itemId}`,
        {
            name: name,
            description: desc,
            price: price,
            imageUrl: image,
            categoryUUID: category
        },
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("jwt")}`
            }
        }
    );
    return data;
}

export const deleteItem = async (itemId: string) => {
    await axios.delete(
        `${baseUrl}/items/${itemId}`,
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("jwt")}`
            }
        }
    );
}