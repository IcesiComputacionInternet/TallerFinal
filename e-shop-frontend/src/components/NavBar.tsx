import { NavigateFunction, useNavigate } from "react-router-dom";
interface NavBarProps {
  role: string;
}
const NavBar = ({ role }: NavBarProps) => {
  const navigation: NavigateFunction = useNavigate();
  return (
    <nav className="navbar fixed-top navbar-light bg-dark">
      <div className="container-fluid">
        <a className="navbar-brand me-2" href="https://mdbgo.com/">
          <img
            src="https://mdbcdn.b-cdn.net/img/logo/mdb-transaprent-noshadows.webp"
            height="16"
            alt="MDB Logo"
            loading="lazy"
          />
        </a>
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="#">
            Productos
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="#">
            Usuarios
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="#">
            Roles
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="#">
            Categorias
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="#">
            Ordenes
          </a>
        )}
        {role === "SHOP" && (
          <a className="navbar-brand text-white" href="#">
            Productos
          </a>
        )}
        {role === "SHOP" && (
          <a className="navbar-brand text-white" href="#">
            Ordenes
          </a>
        )}
        <div className="d-flex align-items-center">
          {localStorage.getItem("jwt") == null && (
            <button
              type="button"
              className="btn btn-primary me-3"
              onClick={() => navigation("/login")}
            >
              Login
            </button>
          )}
          {localStorage.getItem("jwt") !== null && (
            <button
              type="button"
              className="btn btn-danger me-3"
              onClick={() => {
                localStorage.removeItem("jwt");
                sessionStorage.removeItem("username");
                navigation("/");
              }}
            >
              Logout
            </button>
          )}
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
