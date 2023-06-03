import axios from "axios";
import authHeader from "./authHeader";

interface UserDTO {
  email: string;
  password: string;
  phoneNumber: string;
  firstName: string;
  lastName: string;
  address: string;
  birthDate: string;
  role: string;
}

const URL_API = "http://localhost:8080/api/";

const addUser = (userDTO: UserDTO) => {
  return axios.post(`${URL_API}users/create`, userDTO, {
    headers: authHeader(),
  });
};

const UserServices = {
  addUser,
};

export default UserServices;
