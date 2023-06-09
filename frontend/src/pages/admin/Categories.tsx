import { useEffect, useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
import AdminNavbar from "../../components/AdminNavbar";

const baseUrl = "http://localhost:8091";

function Categories (){

  const navigation : NavigateFunction = useNavigate();

  const [categories, setCategories] = useState([]);

  useEffect(() => {
    async function getData() {

      const resultProducts= await getCategories();
      setCategories(resultProducts);
    }
    getData();
  }, []);

  const handleClick = async (event: any) => {
    event.preventDefault();
    navigation("/admin/categories/create");
  };

  return (
    <>
     <AdminNavbar />
     <br />
     {categories.length > 0 ?(
        <div className="container mt-4">
          <p className="h4">Categorias añadidas</p>
          <br />
          <table className="table table-striped-columns" style={{tableLayout:"fixed"}}>
            <thead>
                <tr>
                <th scope="col">Nombre </th>
                <th scope="col">Descripción</th>
                </tr>
            </thead>
            <tbody>
              {categories.map((category, index) => (
                <tr key={index}>
                  <td>{category.name}</td>
                  <td>{category.description}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="d-flex justify-content-end">
            <button type="button" className="btn btn-primary" onClick={handleClick}>Agregar categoria</button>
          </div>
        </div>
        
     ) : (
        <div className="container">
          <p className="h4">No hay categorias añadidas</p>
        </div>
     )}
    </>
  );
      
}

async function getCategories(){

  const categories = await axios.get(
    baseUrl+"/categories",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )

  return categories.data;
}
    
export default Categories;