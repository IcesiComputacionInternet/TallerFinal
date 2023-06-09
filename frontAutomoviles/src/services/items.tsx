import axios from "axios";

const baseUrl = "http://localhost:8080";

export const getItemsPage = async (page: number) => {
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