import { useEffect, useState} from "react";
import axios from "axios";
import Logout from "./Logout"

const baseUrl = "http://localhost:8080";

function HomeAdmin (){

    return (
        <>
          <nav className="navbar navbar-expand-lg bg-body-tertiary" style={{backgroundColor: "#e3f2fd"}}>
            <div className="container-fluid">
                <a className="navbar-brand" href="/">E-Shop</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                    <a className="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link active" aria-current="page" href="#">Users</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">Items</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">Orders</a>
                    </li>
                </ul>
                <Logout />
                </div>
            </div>
          </nav>
          <p className="h4">Users registers</p>
          <table className="table-responsive">
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
          <table className="table-responsive">
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
          <table className="table-responsive">
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


