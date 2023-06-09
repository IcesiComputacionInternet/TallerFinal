import { useEffect, useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
import Navbar from "../../components/Navbar";

const baseUrl = "http://localhost:8091";

function Users (){

  const navigation : NavigateFunction = useNavigate();

  const [users, setUsers] = useState([]);

  useEffect(() => {
    async function getData() {

      const resultUsers = await getUsers();
      setUsers(resultUsers);
    }
    getData();
  }, []);

  const handleClick = async (event: any) => {
    event.preventDefault();
    navigation("/admin/users/create");
  };

  return (
    <>
     <Navbar />
     <br />
     {users.length > 0 ?(
        <div className="container mt-4">
          <p className="h4">Usuarios registrados</p>
          <br />
          <table className="table table-striped-columns" style={{tableLayout:"fixed"}}>
            <thead>
                <tr>
                <th scope="col">Nombre </th>
                <th scope="col">Apellido</th>
                <th scope="col">Correo</th>
                <th scope="col">Número de teléfono</th>
                <th scope="col">Dirección</th>
                <th scope="col">Fecha de Nacimiento</th>
                <th scope="col">Rol</th>
                </tr>
            </thead>
            <tbody>
              {users.map((user, index) => (
                <tr key={index}>
                  <td>{user.firstName}</td>
                  <td>{user.lastName}</td>
                  <td>{user.email}</td>
                  <td>{user.phoneNumber}</td>
                  <td>{user.address}</td>
                  <td>{user.birthday}</td>
                  <td>{user.roleName}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="d-flex justify-content-end">
            <button type="button" className="btn btn-primary" onClick={handleClick}>Agregar usuario</button>
          </div>
        </div>
        
     ) : (
        <div className="container">
          <p className="h4">No hay usuarios registrados</p>
        </div>
     )}
    </>
  );
      
}

async function getUsers(){

  const users = await axios.get(
    baseUrl+"/users",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )
  return users.data;
}
    
export default Users;


