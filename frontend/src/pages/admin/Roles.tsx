import { useEffect, useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
import AdminNavbar from "../../components/AdminNavbar";

const baseUrl = "http://localhost:8091";

function Roles (){

  const navigation : NavigateFunction = useNavigate();

  const [currentUser, setCurrentUser] = useState("");
  const [roles, setRoles] = useState([]);

  useEffect(() => {

    if(localStorage.getItem("jwt")){
      const user = localStorage.getItem("currentRole");

      if(user){
        setCurrentUser(user);
      }
    }else{
      navigation("/NotFound");
    }

    async function getData() {

      const resultUsers = await getRoles();
      setRoles(resultUsers);
    }
    getData();
  }, []);

  const handleClick = async (event: any) => {
    event.preventDefault();
    navigation("/admin/roles/create");
  };

  if(currentUser !== "ADMIN"){
    navigation("/NotFound");
  }

  return (
    <>
     <AdminNavbar />
     <br />
     {roles.length > 0 ?(
        <div className="container mt-4">
          <p className="h4">Roles registrados</p>
          <br />
          <table className="table table-striped-columns" style={{tableLayout:"fixed"}}>
            <thead>
                <tr>
                <th scope="col">Nombre </th>
                <th scope="col">Descripción</th>
                </tr>
            </thead>
            <tbody>
              {roles.map((rol, index) => (
                <tr key={index}>
                  <td>{rol.roleName}</td>
                  <td>{rol.description}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="d-flex justify-content-end">
            <button type="button" className="btn btn-primary" onClick={handleClick}>Agregar rol</button>
          </div>
        </div>
        
     ) : (
        <div className="container">
          <p className="h4">No hay roles registrados</p>
        </div>
     )}
    </>
  );
      
}

async function getRoles(){

  const roles = await axios.get(
    baseUrl+"/roles",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )

  return roles.data;
}
    
export default Roles;


