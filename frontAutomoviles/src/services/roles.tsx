import axios from "axios";

const baseUrl = "http://localhost:8080";

export const getRolesPage = async (page: number) => {
    const {data} = await axios.get(
        `${baseUrl}/roles?page=${page}`,
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

export const postRole = async (name:string, desc:string) => {
    const {data} = await axios.post(
        `${baseUrl}/roles`,
        {
            roleName: name,
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
