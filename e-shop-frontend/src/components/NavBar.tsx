import { NavigateFunction, useNavigate } from "react-router-dom";
interface NavBarProps {
  role: string;
}
const NavBar = ({ role }: NavBarProps) => {
  const navigation: NavigateFunction = useNavigate();
  return (
    <nav className="navbar fixed-top navbar-light bg-dark">
      <div className="container-fluid">
        <a className="navbar-brand me-2" href="/">
          <img
            src="./logo.png"
            height="32"
            width="32"
            alt="furniture logo"
            loading="lazy"
          />
        </a>
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="/items">
            Productos
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="/users">
            Usuarios
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="/roles">
            Roles
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="/category">
            Categorias
          </a>
        )}
        {role === "ADMIN" && (
          <a className="navbar-brand text-white" href="/orders">
            Ordenes
          </a>
        )}
        {role === "SHOP" && (
          <a className="navbar-brand text-white" href="/createItem">
            Productos
          </a>
        )}
        {role === "SHOP" && (
          <a className="navbar-brand text-white" href="/changeStatusOrder">
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
