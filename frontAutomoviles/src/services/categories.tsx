import axios from "axios";

const baseUrl = "http://localhost:8080";

export const getCategoriesPage = async (page: number) => {
    const {data} = await axios.get(
        `${baseUrl}/categories?page=${page}`,
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

export const updateCategory = async (categoryId: string, newName:string, newDesc:string) => {
    const {data} = await axios.patch(
        `${baseUrl}/categories/${categoryId}`,
        {
            name: newName,
            description: newDesc
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

export const postCategory = async (name:string, desc:string) => {
    const {data} = await axios.post(
        `${baseUrl}/categories`,
        {
            name: name,
            description: desc
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