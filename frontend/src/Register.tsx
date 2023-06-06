import React, { useState } from "react";
import axios from "axios";
const baseUrl = "http://localhost:8080";

interface Props {
  onRegistrationComplete: () => void;
}

const Register = ({ onRegistrationComplete }: Props) => {
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [birthdate, setBirthdate] = useState("");

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      const response = await axios.post(baseUrl + "/register", {
        email,
        phone,
        password,
        address,
        firstName,
        lastName,
        birthdate,
      });

      if (response.data.success) {
        onRegistrationComplete(); // Llamar a la función onRegistrationComplete si el registro es exitoso
        console.log("Registration successful!", response.data);
      }
    } catch (error) {
      console.error("Registration failed!", error);
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title text-center">Registro</h3>
              <form onSubmit={handleSubmit}>
                <div className="form-group">
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
                    value={phone}
                    onChange={(event) => setPhone(event.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label>Password:</label>
                  <input
                    type="password"
                    className="form-control"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                  />
                </div>
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
                </div>
                <div className="form-group">
                  <label>Fecha de Nacimiento:</label>
                  <input
                    type="date"
                    className="form-control"
                    value={birthdate}
                    onChange={(event) => setBirthdate(event.target.value)}
                  />
                </div>
                <div className="d-flex justify-content-center">
                  <button type="submit" className="btn btn-primary">
                    Registrarse
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