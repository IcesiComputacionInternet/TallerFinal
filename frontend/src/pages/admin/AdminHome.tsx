import { useEffect, useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
import Navbar from "../../components/Navbar";

const baseUrl = "http://localhost:8091";

function HomeAdmin (){

  const navigation : NavigateFunction = useNavigate();

  const [currentUser, setCurrentUser] = useState("");

  const [orders, setOrders] = useState([]);
  const [users, setUsers] = useState([]);
  const [items, setItems] = useState([]);

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

      const resultUsers = await getUsers();
      setUsers(resultUsers);

      const resultItems = await getItems();
      setItems(resultItems);

      const resultOrders = await getOrders();
      setOrders(resultOrders);
    }

    getData();
  }, []);

  const handleClickMoreUsers = async (event: any) => {
    event.preventDefault();
    navigation("/admin/users");
  };

  const handleClickMoreItems = async (event: any) => {
    event.preventDefault();
    navigation("/items");
  };


  const handleClickMoreOrders = async (event: any) => {
    event.preventDefault();
    navigation("/orders");
  };

  if(currentUser !== "ADMIN"){
    navigation("/NotFound");
  }

  return (
    <>
     <Navbar />
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
                </tr>
            </thead>
            <tbody>
              {users.map((user, index) => (
                <tr key={index}>
                  <td>{user.firstName}</td>
                  <td>{user.lastName}</td>
                  <td>{user.email}</td>
                  <td>{user.phoneNumber}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="d-flex justify-content-end">
            <button type="button" className="btn btn-link" onClick={handleClickMoreUsers}>Ver más usuarios</button>
          </div>
        </div>

     ) : (
        <div className="container">
          <p className="h4">No hay usuarios registrados</p>
        </div>
     )}    

     <br />
     {items.length > 0 ?(
        <div className="container">
          <p className="h4">ítems registrados</p>
        <br />
          <table className="table table-striped-columns" style={{tableLayout:"fixed"}}>
            <thead>
                <tr>
                <th scope="col">Nombre</th>
                <th scope="col">Marca</th>
                <th scope="col">Precio</th>
                <th scope="col">Categoría</th>
                </tr>
            </thead>
            <tbody>
              {items.map((item, index) => (
                  <tr key={index}>
                    <td>{item.name}</td>
                    <td>{item.marca}</td>
                    <td>${item.price}</td>
                    <td>{item.category}</td>
                  </tr>
                ))}
            </tbody>
          </table>
          <div className="d-flex justify-content-end">
            <button type="button" className="btn btn-link" onClick={handleClickMoreItems}>Ver más items</button>
          </div>
        </div>
      ) : (
        <div className="container">
          <p className="h4">No hay ítems registrados</p>
        </div>
      )}   

    <br />
     {orders.length > 0 ?(
      <div className="container">
        <p className="h4">Órdenes registradas</p>
       <br />
       <div className="table-responsive">
        <table className="table table-striped-columns" style={{tableLayout:"fixed"}}>
            <thead>
                <tr>
                <th scope="col">Email del usuario</th>
                <th scope="col">Celular del usuario</th>
                <th scope="col">Total</th>
                <th scope="col">Estado</th>
                </tr>
            </thead>
            <tbody>
            {orders.map((order, index) => (
                <tr key={index}>
                  <td>{order.userEmail}</td>
                  <td>{order.userPhoneNumber}</td>
                  <td>${order.total}</td>
                  <td>{order.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
       </div>
       <div className="d-flex justify-content-end">
         <button type="button" className="btn btn-link" onClick={handleClickMoreOrders}>Ver más órdenes </button>
      </div>
      </div>
      ) : (
        <div className="container">
          <p className="h4">No hay ordenes registradas</p>
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

  return users.data.slice(0,5);
}

async function getItems(){

  const items = await axios.get(
    baseUrl+"/items",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )

  return items.data.slice(0,5);
}

async function getOrders(){

  const orders = await axios.get(
    baseUrl+"/orders",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )

  return orders.data.slice(0,5);
}

    
export default HomeAdmin;


