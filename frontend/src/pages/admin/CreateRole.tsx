import { useState, useEffect } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import axios from "axios";

const baseUrl = "http://localhost:8091";

function CreateRoles() {

  const [currentUser, setCurrentUser] = useState("");
  const navigation : NavigateFunction = useNavigate();

  const [roleName, setRoleName] = useState("");
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
          const response = await axios.post(baseUrl + "/roles",
            {
                roleName,
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
            alert("Role creation successful!");
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
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title text-center">Crear rol</h3>
              <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <div className="form-group">
                    <label>Nombre del rol:</label>
                    <input
                        type="text"
                        className="form-control"
                        value={roleName}
                        onChange={(event) => setRoleName(event.target.value)}
                    />
                    </div>
                    <div className="form-group">
                    <label>Descripción del rol:</label>
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
                            Crear rol
                        </button>
                     </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateRoles;