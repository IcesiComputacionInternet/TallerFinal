import AdminNavbar from "../../components/AdminNavbar";

const baseUrl = "http://localhost:8091";

function Orders (){

  return (
    <>
     <AdminNavbar/>     
     <br />
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

    
export default Orders;


