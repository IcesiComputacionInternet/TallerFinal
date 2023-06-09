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

  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
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
    <div className="container" style={{marginTop:"5%"}}>
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title text-center">Registro</h3>
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
                    required
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
                    required
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
  );
};

export default Register;