import React, { useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import axios from "axios";

const baseUrl = "http://localhost:8091";

interface Props {
  onRegistrationComplete: () => void;
}

interface UserData {
  email: string;
  phoneNumber: string;
  password: string;
  address?: string;
  firstName?: string;
  lastName?: string;
  roleName: string;
  birthday?: string;
}

const Register = ({ onRegistrationComplete }: Props) => {

  const navigation : NavigateFunction = useNavigate();

  const [email, setEmail] = useState(null);
  const [phoneNumber, setPhoneNumber] = useState(null);
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [birthday, setBirthday] = useState("");

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const requestData:UserData = {
          email,
          phoneNumber,
          password,
          roleName:"USER",
        };
        
        if (firstName) {
          requestData.firstName = firstName;
        }
        if (lastName) {
          requestData.lastName = lastName;
        }
        if (address) {
          requestData.address = address;
        }
        if (birthday) {
          requestData.birthday = birthday;
        }

        const response = await axios.post(baseUrl + "/users/register", requestData);
      
      if (response.status == 200) {
        onRegistrationComplete();
        alert("Registration successful!");
        navigation("/login");
      }
    } catch (error) {
        alert("Registration failed! "+error.response.data.details[0].errorMessage);
    }
  };

  const handleClick = async (event: any) => {
    event.preventDefault();
    navigation("/login");
  };

  return (
    <section className="h-80 gradient-form" style={{backgroundColor:"#eee", marginTop:"80px"}}>
        <div className="container py-5 h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-xl-10">
              <div className="card rounded-3 text-black">
                <div className="row g-0">
                  
                <div className="col-lg-6 d-flex align-items-center" style={{backgroundImage:"linear-gradient(to right, #52BDEB, #4A68F6)"}}>
                    <div className="container" style={{display:"flex", justifyContent:"center", alignItems:"center", textAlign:"center", flexDirection:"column"}}>
                      <img src="data:image/svg+xml;charset=UTF-8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22black%22%20class%3D%22bi%20bi-bag-heart-fill%22%20viewBox%3D%220%200%2016%2016%22%3E%0A%20%20%3Cpath%20d%3D%22M11.5%204v-.5a3.5%203.5%200%201%200-7%200V4H1v10a2%202%200%200%200%202%202h10a2%202%200%200%200%202-2V4h-3.5ZM8%201a2.5%202.5%200%200%201%202.5%202.5V4h-5v-.5A2.5%202.5%200%200%201%208%201Zm0%206.993c1.664-1.711%205.825%201.283%200%205.132-5.825-3.85-1.664-6.843%200-5.132Z%22%2F%3E%0A%3C%2Fsvg%3E"  
                          alt="Logo" width="150" height="150" className="d-inline-block align-text-center"/>
                      <h3 className="mb-4">EShop</h3>
                    </div>
                  </div>
                  <div className="col-lg-6">
                    <div className="card-body p-md-5 mx-md-4">

                      <div className="text-center">
                        <h4 className="mt-1 mb-5 pb-1">Iniciar sesión</h4>
                      </div>

                      <form onSubmit={handleSubmit}>
                        <div className="form-group">
                        <div className="form-group">
                          <label>Nombres:</label>
                          <input
                            type="text"
                            className="form-control"
                            value={firstName}
                            onChange={(event) => setFirstName(event.target.value)}
                          />
                        </div>
                        <div className="form-group">
                          <label>Apellidos:</label>
                          <input
                            type="text"
                            className="form-control"
                            value={lastName}
                            onChange={(event) => setLastName(event.target.value)}
                          />
                          <div className="form-group">
                          <label>Dirección:</label>
                          <input
                            type="text"
                            className="form-control"
                            value={address}
                            onChange={(event) => setAddress(event.target.value)}
                          />
                        </div>
                        <div className="form-group">
                          <label>Fecha de Nacimiento:</label>
                          <input
                            type="date"
                            className="form-control"
                            value={birthday}
                            onChange={(event) => setBirthday(event.target.value)}
                          />
                        </div>
                        </div>
                          <label>Email:</label>
                          <input
                            type="email"
                            className="form-control"
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                            
                          />
                        </div>
                        <div className="form-group">
                          <label>Teléfono:</label>
                          <input
                            type="tel"
                            className="form-control"
                            placeholder="+573100000000"
                            value={phoneNumber}
                            onChange={(event) => setPhoneNumber(event.target.value)}
                            
                          />
                        </div>
                        <div className="form-group">
                          <label>Password:</label>
                          <input
                            type="password"
                            className="form-control"
                            value={password}
                            onChange={(event) => setPassword(event.target.value)}
                            required
                          />
                        </div>
                        <br />
                        <div className="d-flex justify-content-center">
                          <button type="submit" className="btn btn-primary">
                            Registrarse
                          </button>
                          <button type="button" className="btn btn-secondary" onClick={handleClick}>
                            Volver
                          </button>
                        </div>
                      </form>
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

export default Register;