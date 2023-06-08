import styles from "../../styles/Admin.module.css";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Button } from "@mui/material";
import OrderItem from "../../components/OrderItem";
import axios from "axios";

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
    return (
        <div className={styles.maxContainer} >
            <div className={styles.insideContainer}>
                <div className={styles.topBar}>
                    <h1 style={{marginRight:30}}>Orders List</h1>
                </div>
                <div className={styles.itemsSection}>
                    <div className={styles.itemsList}>
                        <OrderItem orderId="dsad5asd65asd" state="Pending" date="2021-10-10" total={100}/>
                        
                    </div>
                </div>
            </div>
        </div>
    )
}

/**export async function getServerSideProps(context:any){
    const data = await axios.get("http://localhost:9090/salesOrder/all",{
        headers:{
            "Authorization":"Bearer " + localStorage.getItem("token"),
            "Content-Type":"application/json"
        }
    })
    return{
        props:{
            data
        }
    }
}**/