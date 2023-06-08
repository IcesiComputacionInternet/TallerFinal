import { Button } from "@mui/material";
import styles from "../../styles/Products.module.css";
import AddIcon from '@mui/icons-material/Add';
import ProductItem from "../../components/ProductItem";
import { createTheme, ThemeProvider } from '@mui/material/styles';
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

export default function Producst(props:any){
    const handleAddProduct = () => {
        window.location.href = "/products/newProduct";
    }
    return(
        <div className={styles.maxContainer} >
            <div className={styles.insideContainer}>
                <div className={styles.topBar}>
                    <h1 style={{marginRight:30}}>Products List</h1>
                    <ThemeProvider theme={theme}>

                    <Button variant="contained" 
                    onClick={handleAddProduct}
                    color="primary"
                    endIcon={<AddIcon/>} 
                    style={{borderRadius:18,height: "40px", width: "150px", fontSize: "12px", fontWeight: "bold"}}>Add Product</Button>
                    </ThemeProvider>
                </div>
                <div className={styles.itemsSection}>
                    <div className={styles.itemsList}>
                        <ProductItem name="Product 1" price={100} amount={10} category="Category 1" productId="1"/>
                        
                    </div>
                </div>
            </div>
        </div>
    )
}

/**export async function getServerSideProps(context:any){
    const data = await axios.get("http://localhost:9090/item/all",{
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