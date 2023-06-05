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

const getUsers = () => {
  return axios.get(`${URL_API}users/all`, {
    headers: authHeader(),
  });
};

const update = (userDTO: UserDTO) => {
  return axios.put(`${URL_API}users/update`, userDTO, {
    headers: authHeader(),
  });
};

const deleteUser = (email: any) => {
  return axios.delete(`${URL_API}users/delete`, {
    data: email.toString(),
    headers: authHeader(),
  });
};

const UserServices = {
  addUser,
  getUsers,
  update,
  deleteUser,
};

export default UserServices;
