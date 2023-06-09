import axios from "axios";

const baseUrl = "http://localhost:8080";

export const login = async (user: string, password: string) => {
    const {data} = await axios.post(
        `${baseUrl}/token`,
        {
            username: user,
            password: password
        },
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "Content-Type": "application/json"
            }
        }
    );
    if(data.token){
        localStorage.setItem("jwt", data.token);
        localStorage.setItem("userId", data.userId);
        localStorage.setItem("role", data.role);
        return true;
    }else{
        return false;
    }
}