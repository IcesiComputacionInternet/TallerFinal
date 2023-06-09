import styles from "../styles/User.module.css";
import { Button } from "@mui/material";

export default function UserView() {
    return (
        <div className={styles.maxContainer}>
            <div className={styles.promotionBanner}>
                    <h1 style={{color:"#FFFFFF"}}>Up to 50% discount in your new TV!</h1>
            </div>
            <Button variant="contained"
            style={{borderRadius:18,height: "40px", width: "150px", fontSize: "12px", fontWeight: "bold", marginTop: 20, marginLeft: 20}}
            onClick={()=>window.location.href="/products/showProducts"}>Buy Now</Button>

        </div>
    );
}
