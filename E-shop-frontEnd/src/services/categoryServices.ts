import axios from "axios";
import authHeader from "./authHeader";

const URL_API = "http://localhost:8080/api/categories/";

interface categoryProps{
    name: string
    description: string
}

const create = (role:categoryProps) => {
        return axios.post(`${URL_API}create`,role,{
            headers: authHeader()
        })
}

const update = (role:categoryProps) => {
    return axios.put(`${URL_API}update`,role,{
        headers: authHeader()
    })

}

const deleteRole = (categoryName:string) =>{
    return axios.delete(`${URL_API}delete`,{
        data: categoryName, 
        headers: authHeader()
    })
}

const getRoles = () => {
    return axios.get(`${URL_API}/all`,{
        headers: authHeader()
    })
}

const roleServices = {
    create, 
    update,
    deleteRole,
    getRoles
}

export default roleServices