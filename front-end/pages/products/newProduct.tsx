import styles from "../../styles/Products.module.css";
import { Button, Divider } from "@mui/material";
import {TextField} from "@mui/material";
import Autocomplete from '@mui/material/Autocomplete';

const options = [{ label: 'The Shawshank Redemption', year: 1994 },{label:'Hey',year:1886} ];

export default function NewProduct(){
const handleCancel = () => {
    window.location.href = "/products";
    };

    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>New Product</h1>
                    <Divider />
                    <h2>Product name</h2>    
                    <TextField id="outlined-basic" label="Name" variant="outlined" size="small"/>
                    <h2>Price</h2>
                    <TextField id="outlined-basic" label="Price" variant="outlined" size="small"/>
                    <h2>Brand</h2>
                    <TextField id="outlined-basic" label="Brand" variant="outlined" size="small"/>
                    <h2>Initial stock</h2>
                    <TextField id="outlined-basic" label="Stock" variant="outlined" size="small"/>
                    <h2>Category</h2>
                    <Autocomplete
                        disablePortal
                        id="combo-box-demo"
                        options={options}
                        sx={{ width: 300 }}
                        renderInput={(params) => <TextField {...params} size="small" label="Movie" />}
                    />
                    <h2>Description</h2>
                    <TextField id="outlined-basic" label="Description" variant="outlined" multiline rows={3} size="small"/>
                    <div className={styles.buttonsNewProduct}> 
                        <Button variant="contained" color="success">Save</Button>
                        <div style={{width:30}}></div>
                        <Button variant="contained" color="error" onClick={handleCancel}>Cancel</Button>
                    </div>
                </div>
            </div>
        </div>
    )
}