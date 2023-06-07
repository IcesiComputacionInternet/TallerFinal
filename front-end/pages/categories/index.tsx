import { Button } from "@mui/material";
import styles from "../../styles/Products.module.css";
import AddIcon from '@mui/icons-material/Add';
import ProductItem from "../../components/ProductItem";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import CategoryItem from "../../components/CategoryItem";

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
                        <CategoryItem name="Category 1" description="Description 1"/>
                    </div>
                </div>
            </div>
        </div>
    )
}