import AdminNavbar from "../../components/AdminNavbar";

const baseUrl = "http://localhost:8091";

function Items (){

  return (
    <>
     <AdminNavbar/>
     <br />
     <div className="container">
        <p className="h4">Items Disponibles</p>
       <br />
        <table className="table table-striped-columns" table-layout="fixed">
          <thead>
              <tr>
              <th scope="col">Nombre</th>
              <th scope="col">Marca</th>
              <th scope="col">Precio</th>
              </tr>
          </thead>
          <tbody>
              <tr>
              <td>Mark</td>
              <td>Otto</td>
              <td>320</td>
              </tr>
          </tbody>
        </table>
      </div>
    </>
  );
      
}

    
export default Items;


