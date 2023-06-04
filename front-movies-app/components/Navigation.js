import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';


function Navigation() {

    return (
        <nav className="navbar navbar-expand">
            <div className="container-fluid">
                <div className="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                        <a className="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li className="nav-item">
                        <a className="nav-link" href="#">Features</a>
                        </li>
                        <li className="nav-item dropdown" >
                            <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Dropdown link
                            </a>
                            <ul className="dropdown-menu">
                                <li><a className="dropdown-item" href="#">Action</a></li>
                                <li><a className="dropdown-item" href="#">Another action</a></li>
                                <li><a className="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <form className="d-flex" role="search">
                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                
                <button className="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </nav>
      )
}

export default Navigation;