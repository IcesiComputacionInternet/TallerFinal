import { useEffect, useState} from "react";
import axios from "axios";
import Logout from "./Logout"

const baseUrl = "http://localhost:8091";

function HomeAdmin (){

    return (
        <>
          <div className="container-fluid">
            <nav className="navbar navbar-expand-lg bg-body-tertiary" style={{backgroundColor: "#FAD011"}}>
              <div className="container-fluid">
                  <a className="navbar-brand" href="/HomeAdmin">
                    <img src="data:image/svg+xml;charset=UTF-8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22currentColor%22%20class%3D%22bi%20bi-bag%22%20viewBox%3D%220%200%2016%2016%22%3E%0A%20%20%3Cpath%20d%3D%22M8%201a2.5%202.5%200%200%201%202.5%202.5V4h-5v-.5A2.5%202.5%200%200%201%208%201zm3.5%203v-.5a3.5%203.5%200%201%200-7%200V4H1v10a2%202%200%200%200%202%202h10a2%202%200%200%200%202-2V4h-3.5zM2%205h12v9a1%201%200%200%201-1%201H3a1%201%200%200%201-1-1V5z%22%2F%3E%0A%3C%2Fsvg%3E" alt="Logo" width="25" height="25" className="d-inline-block align-text-top" />
                    EShop
                  </a>
                  <div className="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul className="navbar-nav me-auto mb-2 mb-lg-0" style={{fontSize: "1.25rem"}}>
                      <li className="nav-item">
                      <a className="nav-link active" aria-current="page" href="#">Users</a>
                      </li>
                      <li className="nav-item">
                      <a className="nav-link active" aria-current="page" href="#">Items</a>
                      </li>
                      <li className="nav-item">
                      <a className="nav-link active" aria-current="page" href="#">Orders</a>
                      </li>
                      <li className="nav-item">
                      <a className="nav-link active" aria-current="page" href="/About">About</a>
                      </li>
                  </ul>
                  <Logout />
                  </div>
              </div>
            </nav>
          </div>
         <br />
          <p className="h4" style={{textAlign:"start"}}>Users registers</p>
          <table className="table table-striped-columns">
            <thead>
                <tr>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Email</th>
                <th scope="col">Phone number</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>320</td>
                </tr>
            </tbody>
          </table>

          <p className="h4">Items Availables</p>
          <table className="table table-striped">
            <thead>
                <tr>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Marca</th>
                <th scope="col">Description</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>320</td>
                </tr>
            </tbody>
          </table>

          <p className="h4">Orders</p>
          <table className="table table-striped">
            <thead>
                <tr>
                <th scope="col">Status</th>
                <th scope="col">Total</th>
                <th scope="col">User</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>320</td>
                </tr>
            </tbody>
          </table>
        </>
      );
      
}

    
export default HomeAdmin;


