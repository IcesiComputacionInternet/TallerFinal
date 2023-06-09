import styles from "../../styles/Admin.module.css";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Button } from "@mui/material";
import OrderItem from "../../components/OrderItem";
import axios from "axios";
import {useState,useEffect} from "react"

const theme = createTheme({
    palette: {
        primary: {
            main: "#21897E",
        },
        secondary: {
            main: "#3BA99C",
        }
    },
});

export default function Orders(props:any) {
    const [orders,setOrders] = useState([])
    useEffect(() => {
        axios.get("http://localhost:9090/salesOrder/all",{
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("token")}`,
            }
        }).then((response) => {
            setOrders(response.data);
            console.log(response.data);
        }).catch((error) => {
            console.log(error);
        });
    },[]);         


    return (
        <div className={styles.maxContainer} >
            <div className={styles.insideContainer}>
                <div className={styles.topBar}>
                    <h1 style={{marginRight:30}}>Orders List</h1>
                </div>
                <div className={styles.itemsSection}>
                    <div className={styles.itemsList}>
                        {orders.map((order:any) => {
                            return <OrderItem orderId={order.orderId} state={order.status}  total={order.total}/>
                        })
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}
