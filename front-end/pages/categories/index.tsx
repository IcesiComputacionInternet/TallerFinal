import { Button } from "@mui/material";
import styles from "../../styles/Products.module.css";
import AddIcon from '@mui/icons-material/Add';
import ProductItem from "../../components/ProductItem";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import CategoryItem from "../../components/CategoryItem";
import axios from "axios";
import { useEffect,useState } from "react";

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

export default function Categories() {
    const [categories,setCategories] = useState([]);
    const [visible,setVisible] = useState(true);
    useEffect(() => {
        axios.get("http://localhost:9090/category/all",{
        headers:{
            "Authorization":"Bearer " + localStorage.getItem("token"),
            "Content-Type":"application/json"
        }
    }).then((response) => {
        console.log(response.data);
        setCategories(response.data);
    }).catch((error) => {
        console.log(error);
    })
    },[])
    const handleAgree = () => {
        setVisible(false);
    }
    const handleNewCategory = () => {
        window.location.href = "/categories/new";
    }

    return (
        <div className={styles.maxContainer} >
            <div className={styles.insideContainer}>
                <div className={styles.topBar}>
                    <h1 style={{marginRight:30}}>Categories List</h1>
                    <ThemeProvider theme={theme}>
                        <Button variant="contained"
                            onClick={handleNewCategory}
                            color="primary"
                            endIcon={<AddIcon />}
                            style={{ borderRadius: 18, height: "40px", width: "150px", fontSize: "12px", fontWeight: "bold" }}>Add Category</Button>
                    </ThemeProvider>
                </div>
                <div className={styles.itemsSection}>
                    <div className={styles.itemsList}>
                        {visible? <CategoryItem name="Category 1X" description="Description 1" categoryId="1" handleAgree={handleAgree}/> : <div></div>}
                        {categories.map((category) => {
                            return <CategoryItem name={category.name} description={category.description} categoryId={category.id} handleAgree={() => console.log("")}/>
                        })
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}
