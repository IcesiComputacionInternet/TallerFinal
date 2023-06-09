import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios'

function Navigation() {
  const [current, setCurrent] = useState({});
  const [categories, setCategories] = useState([]);
  const [email, setEmail] = useState('')
  const [orderLink, setOrderLink] = useState('')
  const [isReady, setIsReady] = useState(false)

  const [isLoadingCurrent, setIsLoadingCurrent] = useState(true);
  const [isLoadingCategories, setIsLoadingCategories] = useState(true);

//   console.log(user);

useEffect(() => {
    getCurrentUser();
    getCategories();
    // const orderLink = "/orders/" + email;
  }, []);

  const setOrderEmail = () => {
    let newEmail = current != undefined ? current.data.email : localStorage.getItem("user").email;
    setEmail(newEmail)
    setOrderLink("/orders/" + email)
  }

  useEffect(() => {
    if (isReady) {
        setOrderEmail(current)
    }
  }, [current, isReady])


  async function getCurrentUser() {
    try {
      const { data } = await axios.get(
        "http://localhost:8080/users/CurrentUser",
        {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            MediaType: "application/json",
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        }
      );

      let res = { data };
      console.log(res);
      setCurrent(res);
      localStorage.setItem("user", JSON.stringify(res.data));
      setIsLoadingCurrent(false);
      setIsReady(true)
    } catch (err) {
      console.error("Error fetching current user:", err);
    }
  }

  async function getCategories() {
    try {
      const { data } = await axios.get("http://localhost:8080/categories/all", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          MediaType: "application/json",
          Authorization: "Bearer " + localStorage.getItem("jwt"),
        },
      });

      let res = { data };
      console.log(res);
      setCategories(res);
      setIsLoadingCategories(false);
    } catch (err) {
      console.error("Error fetching categories:", err);
    }
  }

  const removeAccess = () => {
    localStorage.removeItem("jwt");
    localStorage.removeItem("user");
  };

  return (
    <>
      {isLoadingCategories || isLoadingCurrent ? (
        <h1>Loading...</h1>
      ) : (
        <div>
          <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
            crossOrigin="anonymous"
          ></link>
          <script
            defer
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossOrigin="anonymous"
          ></script>

          <nav className="navbar navbar-expand">
            <div className="container-fluid">
              <div className="collapse navbar-collapse" id="navbarNavDropdown">
                <ul className="navbar-nav">
                  <li className="nav-item">
                    <a
                      className="nav-link active"
                      aria-current="page"
                      href="/Home"
                    >
                      Home
                    </a>
                  </li>
                  <li className="nav-item">
                    <a
                      className="nav-link active"
                      aria-current="page"
                      href={orderLink}
                    >
                      My Orders
                    </a>
                  </li>
                  <li className="nav-item">
                    <a
                      className="nav-link active"
                      aria-current="page"
                      href="/Profile"
                    >
                      Profile
                    </a>
                  </li>
                  {current.data.role.name == "ADMIN" ? (
                    <li className="nav-item">
                      <a
                        className="nav-link active"
                        aria-current="page"
                        href="/movies"
                      >
                        Movies
                      </a>
                    </li>
                  ) : (
                    <></>
                  )}
                  <li className="nav-item">
                    <a
                      className="nav-link active"
                      aria-current="page"
                      href="/login"
                      onClick={removeAccess}
                    >
                      Logout
                    </a>
                  </li>
                  <li className="nav-item dropdown">
                    <a
                      className="nav-link dropdown-toggle"
                      href="#"
                      role="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      Categories
                    </a>
                    <ul className="dropdown-menu">
                      {categories.data.map((category) => (
                        <li key={category.name}>
                          <a className="dropdown-item" href="#">
                            {category.name}
                          </a>
                        </li>
                      ))}
                    </ul>
                  </li>
                </ul>
              </div>
              <form className="d-flex" role="search">
                <input
                  className="form-control me-2"
                  type="search"
                  placeholder="Search"
                  aria-label="Search"
                />

                <button type="submit" className="btn btn-outline-success">
                  Search
                </button>
              </form>
            </div>
          </nav>
        </div>
      )}
    </>
  );
}

export default Navigation;
