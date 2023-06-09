import { StoreItem } from "../components/StoreItem"
import {Col, Container, Row} from "react-bootstrap"
import storeItems from "../testData/items.json"
import {UserNavbar} from "../components/UserNavbar.tsx";
import {AdminNavbar} from "../components/AdminNavbar.tsx";
import {ShopNavbar} from "../components/ShopNavbar.tsx";
import {useEffect, useState} from "react";


export function Store() {
    const [role, setRole] = useState<string | null>("none");
    useEffect(() => {
        const role = localStorage.getItem('role');
        setRole(role);
        console.log(role)
    },[])

    let navbarComponent;

    if (role === "USER") {
        navbarComponent = <UserNavbar />;
    } else if (role === "ADMIN") {
        navbarComponent = <AdminNavbar />;
    } else if (role === "SHOP"){
        navbarComponent = <ShopNavbar />;
    }

    return (
        <Container  style= {{ width: "-webkit-max-content"}}>
            {/*{navbarComponent}*/}
            <UserNavbar />;
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
