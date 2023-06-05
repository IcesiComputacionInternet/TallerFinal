import axios from "axios";
import authHeader from "./authHeader";

const URL_API = "http://localhost:8080/api/roles/";

interface roleProps {
  roleName: string;
  description: string;
}

const create = (role: roleProps) => {
  return axios.post(`${URL_API}create`, role, {
    headers: authHeader(),
  });
};

const update = (role: roleProps) => {
  return axios.put(`${URL_API}update`, role, {
    headers: authHeader(),
  });
};

const deleteRole = (role: any) => {
  return axios.delete(`${URL_API}delete`, {
    data: role.toString(),
    headers: authHeader(),
  });
};

const getRoles = () => {
  return axios.get(`${URL_API}all`, {
    headers: authHeader(),
  });
};

const roleServices = {
  create,
  update,
  deleteRole,
  getRoles,
};

export default roleServices;
