import axios from "axios";

const baseUrl = "http://localhost:8080";

export const register = async (userInfo: any) => {
    const {data} = await axios.post(
        `${baseUrl}/eShopUsers`,
        userInfo,
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "Content-Type": "application/json"
            }
        }
    );
    if(data){
        return true;
    }else{
        return false;
    }
}