import React, {useState} from "react";
import axios from "axios";
import {NavigateFunction, useNavigate} from "react-router-dom";

const baseUrl="http://localhost:8091";

interface Props {
    setLogin: (isLoggedIn: boolean) => void;
}


const Login = ({ setLogin }: Props) =>{

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: any) => {
      event.preventDefault();

      try {

        const { data } = await axios.post(
          baseUrl+"/token",
          {
              username,
              password
          },
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
          
          if(role === "ADMIN"){
            console.log("admin")

              navigation("/admin/home");

          }else if(role === "SHOP"){
            console.log("shop")
              navigation("/shop/home");

          }else{

            navigation("/users/home-shop");
          }

        }
          
      } catch (error) {
        alert("Credenciales invalidas "+error.response.data.details[0].errorMessage)
      }
    };

    const handleClick = async (event: any) => {
      event.preventDefault();
      navigation("/register");
    };

    return (
      <section className="h-80 gradient-form" style={{backgroundColor:"#eee", marginTop:"140px"}}>
        <div className="container py-5 h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-xl-10">
              <div className="card rounded-3 text-black">
                <div className="row g-0">
                  <div className="col-lg-6">
                    <div className="card-body p-md-5 mx-md-4">

                      <div className="text-center">
                        <h4 className="mt-1 mb-5 pb-1">Iniciar sesión</h4>
                      </div>

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
                        <div className="d-flex align-items-center justify-content-center pb-4">
                          <p className="mb-0 me-2">¿No tienes una cuenta?</p>
                          <button type="button" className="btn btn-link" onClick={handleClick}>Registrate </button>
                        </div>
                      </form>

                    </div>
                  </div>
                  <div className="col-lg-6 d-flex align-items-center" style={{backgroundImage:"linear-gradient(to right, #52BDEB, #4A68F6)"}}>
                    <div className="container" style={{display:"flex", justifyContent:"center", alignItems:"center", textAlign:"center", flexDirection:"column"}}>
                      <img src="data:image/svg+xml;charset=UTF-8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22black%22%20class%3D%22bi%20bi-bag-heart-fill%22%20viewBox%3D%220%200%2016%2016%22%3E%0A%20%20%3Cpath%20d%3D%22M11.5%204v-.5a3.5%203.5%200%201%200-7%200V4H1v10a2%202%200%200%200%202%202h10a2%202%200%200%200%202-2V4h-3.5ZM8%201a2.5%202.5%200%200%201%202.5%202.5V4h-5v-.5A2.5%202.5%200%200%201%208%201Zm0%206.993c1.664-1.711%205.825%201.283%200%205.132-5.825-3.85-1.664-6.843%200-5.132Z%22%2F%3E%0A%3C%2Fsvg%3E"  
                          alt="Logo" width="150" height="150" className="d-inline-block align-text-center"/>
                      <h3 className="mb-4">EShop</h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
  );
};

export default Login;