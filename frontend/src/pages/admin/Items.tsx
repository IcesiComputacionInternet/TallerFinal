import { useEffect, useState } from "react";
import axios from "axios";
import AdminNavbar from "../../components/AdminNavbar";

const baseUrl = "http://localhost:8091";

function Items (){

  const [items, setItems] = useState([]);

  useEffect(() => {
    async function getData() {

      const resultItems = await getItems();
      setItems(resultItems);
    }

    getData();
  }, []);

  return (
    <>
     <AdminNavbar />
     <br />
     {items.length > 0 ?(
        <div className="container">
          <p className="h4">Items registrados</p>
        <br />
          <table className="table table-striped-columns" style={{tableLayout:"fixed"}}>
            <thead>
                <tr>
                <th scope="col">Nombre</th>
                <th scope="col">Marca</th>
                <th scope="col">Precio</th>
                <th scope="col">Categoria</th>
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
        </div>
      ) : (
        <div className="container">
          <p className="h4">No hay items registrados</p>
        </div>
      )}
    </>
  );
      
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

  return items.data;
}
    
export default Items;


