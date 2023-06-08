import {Nav, Navbar as NavbarBs, NavLink} from "react-bootstrap"
// import { NavLink } from "react-router-dom"
import { NavigateFunction, useNavigate } from "react-router-dom";

export function AdminNavbar() {
    const navigation : NavigateFunction = useNavigate();

    const handleLogOut = () => {
        localStorage.removeItem("jwt");
        localStorage.removeItem("userEmail");
        localStorage.setItem("logged_user", JSON.stringify(false));
        navigation("/");
    }


    return (
        <NavbarBs sticky="top" className="bg-white shadow-sm mb-3">
            <div className="container-fluid">
                <h2>Home</h2>
                <Nav className="me-auto">
                    <Nav.Link href="/roles" as={NavLink} style={{fontSize:"13"}}>
                        New Rol
                    </Nav.Link>
                </Nav>
                <button className="btn btn-outline-danger" type="button" onClick={handleLogOut}>Log out</button>
            </div>
        </NavbarBs>
    )
}
