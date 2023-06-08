import AdminNavbar from "../../components/AdminNavbar";
const baseUrl = "http://localhost:8091";

function Users (){

  return (
    <>
     <AdminNavbar/>
     <br />
     <div className="container">
      <p className="h4">Usuarios registrados</p>
      <br />
        <table className="table table-striped-columns" table-layout="fixed">
          <thead>
              <tr>
              <th scope="col">Nombre </th>
              <th scope="col">Apellido</th>
              <th scope="col">Correo</th>
              <th scope="col">Número de teléfono</th>
              </tr>
          </thead>
          <tbody>
              <tr>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
              <td>320</td>
              </tr>
          </tbody>
        </table>
      </div>
    </>
  );
      
}

    
export default Users;


