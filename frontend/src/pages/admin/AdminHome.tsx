import AdminNavbar from "../../components/AdminNavbar";

function HomeAdmin (){

  return (
    <>
     <AdminNavbar />          
     <div className="container mt-4">
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

      <div className="container">
        <p className="h4">Ordenes pendientes</p>
       <br />
       <div className="table-responsive">
        <table className="table table-striped-columns" table-layout="fixed">
            <thead>
                <tr>
                <th scope="col">Usuario (Email o celular)</th>
                <th scope="col">Total</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <td>Mark</td>
                <td>320</td>
                </tr>
            </tbody>
          </table>
       </div>
      </div>
    </>
  );
      
}

    
export default HomeAdmin;


