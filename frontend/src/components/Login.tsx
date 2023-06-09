import React, {useState} from "react";
import axios from "axios";
import {NavigateFunction, useNavigate} from "react-router-dom";

const baseUrl="http://localhost:8091";

interface Props {
    setLogin: (isLoggedIn: boolean) => void;
}

interface UserData {
  username?: string;
  phoneNumber?: string;
  password: string;
}

const Login = ({ setLogin }: Props) =>{

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: any) => {
      event.preventDefault();

      try {

        const requestData:UserData = {
          password
        };

        if (!username.includes("@")) {
          requestData.phoneNumber = username;

        }else{
          requestData.username = username;
        }

        const { data } = await axios.post(
          baseUrl+"/token", requestData,
          {
            headers: {
              "Access-Control-Allow-Origin": baseUrl,
            },
          }
        );

        if (data.token){
          localStorage.setItem("jwt", data.token);
          setLogin(true);

          const response = await axios.get(baseUrl + "/users/current", {
            headers: {
              "Access-Control-Allow-Origin": baseUrl,
              "MediaType": "application/json",
              "Authorization": "Bearer "+localStorage.getItem('jwt')
            }
          });

          const user=response.data;
          const role=user.roleName
          localStorage.setItem("currentRole",role);
          

          navigation("/users/home-shop");
        }
          
      } catch (error) {
        alert("Invalid credentials "+error.response.data.details[0].errorMessage)
      }
    };

    const handleClick = async (event: any) => {
      event.preventDefault();
      navigation("/register");
    };

    return (
      <div className="container" style={{marginTop:"5%"}}>
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title text-center">Inicio de sesión</h3>
              <br />
              <form onSubmit={handleSubmit}>
                <div className="form-group">
                  <div className="form-floating">
                    <input
                      type="text"
                      className="form-control"
                      value={username}
                      onChange={(event) => setUsername(event.target.value)}
                      id="floatingInput"
                      placeholder="Correo o celular"
                      required
                    />
                    <label htmlFor="floatingInput">Correo o celular:</label>
                  </div>
                  <div className="form-text" id="basic-addon4">El celular no debe tener espacios y debe tener el indicativo</div>
                  <br />
                  <div className="form-floating">
                    <input
                      type="password"
                      className="form-control"
                      value={password}
                      onChange={(event) => setPassword(event.target.value)}
                      id="floatingPassword"
                      placeholder="Contraseña"
                      required
                    />
                    <label htmlFor="floatingPassword">Contraseña:</label>
                  </div>
                </div>
                <br />
                <div className="d-flex justify-content-center">
                  <button type="submit" className="btn btn-primary">
                    Iniciar sesión
                  </button>
                </div>
                <h6></h6>
                <div className="d-flex justify-content-center">
                  <button type="button" className="btn btn-link" onClick={handleClick}>
                    Registrate
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;