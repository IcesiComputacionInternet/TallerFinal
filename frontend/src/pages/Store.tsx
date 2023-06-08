import { StoreItem } from "../components/StoreItem"
import {Col, Container, Row} from "react-bootstrap"
import storeItems from "../testData/items.json"
import {UserNavbar} from "../components/UserNavbar.tsx";
import {AdminNavbar} from "../components/AdminNavbar.tsx";
import {ShopNavbar} from "../components/ShopNavbar.tsx";

export function Store({ userRole }) {
    let navbarComponent;

    if (userRole === "USER") {
        navbarComponent = <UserNavbar />;
    } else if (userRole === "ADMIN") {
        navbarComponent = <AdminNavbar />;
    } else if (userRole === "SHOP"){
        navbarComponent = <ShopNavbar />;
    }
    return (
        <Container  style= {{ width: "-webkit-max-content"}}>
            {navbarComponent}
            <h1>Store</h1>
            <Row md={2} xs={1} lg={3} className="g-3">
                {storeItems.map(item => (
                    <Col key={item.id}>
                        <StoreItem {...item} />
                    </Col>
                ))}
            </Row>
        </Container>


    )
}
