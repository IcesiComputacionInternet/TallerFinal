import {UserNavbar} from "../components/UserNavbar.tsx";
import {AdminNavbar} from "../components/AdminNavbar.tsx";
import {ShopNavbar} from "../components/ShopNavbar.tsx";
import {useEffect, useState} from "react";
import {Container} from "react-bootstrap"
import axios from "axios";

export function Store() {
    const [role, setRole] = useState<string | null>("none");
    const baseUrl = "http://localhost:8080";
    async function userData() {
    const data = await axios.get(
        baseUrl + "/users/" + localStorage.getItem("userEmail"),
        {
            headers: {
                "Access-Control-Allow-Origin": baseUrl,
                "MediaType": "application/json",
                "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
        }
    );
    return data.data;
}
async function getRoleData() {
    const result = await userData();
    setRole(result.role);
    console.log(result.data)
}


useEffect(() => {
    getRoleData()
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
        {navbarComponent}
        </Container>
    )
}
