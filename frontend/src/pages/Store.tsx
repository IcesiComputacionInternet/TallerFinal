import { StoreItem } from "../components/StoreItem"
import {Col, Container, Row} from "react-bootstrap"
import storeItems from "../testData/items.json"
import {Navbar} from "../components/Navbar.tsx";

export function Store() {
    return (
        <Container  style= {{ width: "-webkit-max-content"}}>
            <Navbar />
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
