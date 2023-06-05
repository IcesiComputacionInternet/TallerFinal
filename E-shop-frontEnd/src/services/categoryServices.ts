import axios from "axios";
import authHeader from "./authHeader";

const URL_API = "http://localhost:8080/api/categories/";

interface categoryProps {
  name: string;
  description: string;
}

const create = (category: categoryProps) => {
  return axios.post(`${URL_API}create`, category, {
    headers: authHeader(),
  });
};

const update = (category: categoryProps) => {
  return axios.put(`${URL_API}update`, category, {
    headers: authHeader(),
  });
};

const deleteCategory = (categoryName: string) => {
  return axios.delete(`${URL_API}delete`, {
    data: categoryName,
    headers: authHeader(),
  });
};

const getCategories = () => {
  return axios.get(`${URL_API}all`, {
    headers: authHeader(),
  });
};

const roleServices = {
  create,
  update,
  deleteCategory,
  getCategories,
};

export default roleServices;
