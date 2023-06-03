import axios from "axios";
import authHeader from "./authHeader";

const URL_API = "http://localhost:8080/";

interface Credentials {
  username: string;
  password: string;
}

const login = (credentials: Credentials) => {
  return axios.post(`${URL_API}login`, credentials, {
    headers: authHeader(),
  });
};

const logout = () => {
  localStorage.removeItem("token");
  window.location.href = "/";
};

const authServices = {
  login,
  logout,
};

export default authServices;
