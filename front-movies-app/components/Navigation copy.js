import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';


function Navigation({categories, user}) {

    console.log(user)
    let email = user != undefined ? user.email : localStorage.getItem("user").email
    const orderLink = "/orders/" + email

    const removeAccess = () => {
        localStorage.removeItem("jwt")
        localStorage.removeItem("user")
    }
    

    return (
        <div>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossOrigin="anonymous"></link>
            <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossOrigin="anonymous"></script>
        
        
            <nav className="navbar navbar-expand">
                <div className="container-fluid">
                    <div className="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="/Home">Home</a>
                            </li>
                            <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href={orderLink}>My Orders</a>
                            </li>
                            <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="/Profile">Profile</a>
                            </li>
                            {
                                user.role.name == 'ADMIN' ? 
                                (
                                    <li className="nav-item">
                                    <a className="nav-link active" aria-current="page" href="/movies">Movies</a>
                                    </li>
                                ) :
                                (
                                    <></>
                                )
                            }
                            <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="/login" onClick={removeAccess}>Logout</a>
                            </li>
                            <li className="nav-item dropdown" >
                                <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Categories
                                </a>
                                <ul className="dropdown-menu">
                                    {categories.data.map((category) => (
                                        <li key={category.name}>
                                            <a className="dropdown-item" href="#">{category.name}</a>
                                        </li>
                                    ))}
                                </ul>
                                
                            </li>
                        </ul>
                    </div>
                    <form className="d-flex" role="search">
                    <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                    
                    <button type="submit" className="btn btn-outline-success" >Search</button>
                    </form>
                </div>
            </nav>
        </div>
      )
}

export default Navigation;