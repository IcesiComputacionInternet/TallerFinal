import axios from "axios";

const baseUrl = "http://localhost:8080";

export const getUsersPage = async (page: number) => {
    const {data} = await axios.get(
        `${baseUrl}/eShopUsers?page=${page}`,
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

export const assignRole = async (eShopUserId:string, roleName:string) => {
    const {data} = await axios.patch(
        `${baseUrl}/eShopUsers/${eShopUserId}/role/${roleName}`,
        {},
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