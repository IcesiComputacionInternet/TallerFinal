import axios from "axios";
import authHeader from "./authHeader";

const URL_API = "http://localhost:8080/api/";

interface Credentials {
  username: string;
  password: string;
}

const login = (credentials: Credentials) => {
  return axios.post(`${URL_API}login`, credentials, {
    headers: authHeader(),
  });
};

const validateToken = (response: {
  status: number;
  data: { error_message: string };
}) => {
  if (response.status === 403) {
    localStorage.removeItem("token");
    if (response.data.error_message.startsWith("JWT expired")) {
      window.location.href = "/login?expired=true";
    } else {
      window.location.href = "/login";
    }
  }
};

const logout = () => {
  localStorage.removeItem("token");
  window.location.href = "/";
};

const authServices = {
  login,
  validateToken,
  logout,
};

export default authServices;
