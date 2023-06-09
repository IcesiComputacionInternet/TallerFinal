import React, { useEffect, useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import axios from "axios";
import Navbar from "../../components/Navbar";

const baseUrl = "http://localhost:8091";

function CreateCategories() {

  const navigation : NavigateFunction = useNavigate();
  const [currentUser, setCurrentUser] = useState("");

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");

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

  const handleSubmit = async (event: any) => {
    event.preventDefault();

    try {
          const response = await axios.post(baseUrl + "/categories",
            {
                name,
                description
            },
            {
                headers:{
                    "Access-Control-Allow-Origin": baseUrl,
                    "MediaType" : "application/json",
                    "Authorization":"Bearer "+localStorage.getItem('jwt')
                }
            }
          );
      
        if (response.status == 200) {
            alert("Category creation successful!");
            navigation("/admin/home");
      }
    } catch (error) {
      alert("Creation failed! "+error.response.data.details[0].errorMessage);
    }
  };

  if(currentUser !== "ADMIN"){
    navigation("/NotFound");
  }

  return (
    <>
    <Navbar/>
    <br />
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <div className="card">
              <div className="card-body">
                <h3 className="card-title text-center">Crear categoria</h3>
                <form onSubmit={handleSubmit}>
                  <div className="form-group">
                      <div className="form-group">
                      <label>Nombre de la categoria:</label>
                      <input
                          type="text"
                          className="form-control"
                          value={name}
                          onChange={(event) => setName(event.target.value)}
                      />
                      </div>
                      <div className="form-group">
                      <label>Descripción de la categoria:</label>
                      <textarea 
                          className="form-control"
                          value={description}
                          onChange={(event) => setDescription(event.target.value)}
                          rows={5}
                      />
                      </div>
                      <br />
                      <div className="d-flex justify-content-center">
                          <button type="submit" className="btn btn-primary">
                              Crear categoria
                          </button>
                      </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default CreateCategories;