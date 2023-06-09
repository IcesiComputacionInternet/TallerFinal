import {Col, Container, Nav, Navbar as NavbarBs, NavLink, Row} from "react-bootstrap"
// import { NavLink } from "react-router-dom"
import { NavigateFunction, useNavigate } from "react-router-dom";
import storeItems from "../testData/items.json";
import {OtherViewStore} from "./OtherStoreView.tsx";

export function AdminNavbar() {
    const navigation : NavigateFunction = useNavigate();

    const handleLogOut = () => {
        localStorage.removeItem("jwt");
        localStorage.removeItem("userEmail");
        localStorage.setItem("logged_user", JSON.stringify(false));
        navigation("/");
    }


    return (
        <Container  style= {{ width: "-webkit-max-content"}}>
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
        <h1>Store</h1>
        <Row md={2} xs={1} lg={3} className="g-3">
            {storeItems.map(item => (
                <Col key={item.id}>
                    <OtherViewStore {...item} />
                </Col>
            ))}
        </Row>
    </Container>
    )
}
