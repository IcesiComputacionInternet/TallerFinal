import { useState, useEffect } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import axios from "axios";

const baseUrl = "http://localhost:8091";

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

function CreateUser() {

  const navigation : NavigateFunction = useNavigate();
  const [currentUser, setCurrentUser] = useState("");

  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [birthday, setBirthday] = useState("");
  const [roleName, setRoleName] = useState("");

  useEffect(() => {
    if(localStorage.getItem("jwt")){
      const user = localStorage.getItem("currentRole");

      if(user){
        setCurrentUser(user);
      }
    }else{
      navigation("/NotFound");
    }
    
  }, []);

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
        const requestData:UserData = {
            email,
            phoneNumber,
            password,
            roleName,
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
          
          const response = await axios.post(baseUrl + "/users", requestData,
            {
                headers:{
                    "Access-Control-Allow-Origin": baseUrl,
                    "MediaType" : "application/json",
                    "Authorization":"Bearer "+localStorage.getItem('jwt')
                }
            }
          );
      
        if (response.status == 200) {
            alert("Creation successful!");
            navigation("/admin/home");
      }
    } catch (error) {
      alert("Creation failed! "+error.response.data.details[0].errorMessage);
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title text-center">Agregar usuario</h3>
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
                <label>Rol del usuario:</label>
                <br />
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="ADMIN" 
                    onChange={(event) => setRoleName(event.target.value)}
                    required/>
                    <label className="form-check-label" htmlFor="inlineRadio1">Admin</label>
                </div>
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="SHOP"
                    onChange={(event) => setRoleName(event.target.value)}
                    />
                    <label className="form-check-label" htmlFor="inlineRadio2">Shop</label>
                </div>
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="USER"
                    onChange={(event) => setRoleName(event.target.value)}
                    />
                    <label className="form-check-label" htmlFor="inlineRadio3">User</label>
                </div>
                <br />
                <div className="d-flex justify-content-center">
                  <button type="submit" className="btn btn-primary">
                    Agregar usuario
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateUser;