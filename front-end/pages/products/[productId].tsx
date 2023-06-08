import styles from "../../styles/Products.module.css";
import Divider from "@mui/material/Divider";
import { Button, TextField } from "@mui/material";
import Autocomplete from '@mui/material/Autocomplete';
import axios from "axios";
import { useRef } from "react";
const options = [{ label: 'The Shawshank Redemption', year: 1994 },{label:'Hey',year:1886} ];


export default function ProductItem(props:any) {
    const nameRef = useRef();
    const priceRef = useRef();
    const brandRef = useRef();
    const stockRef = useRef();
    const categoryRef = useRef();
    const descriptionRef = useRef();
    const imgRef = useRef();

    const handleCancel = () => {
        window.location.href = "/products";
    };
    const handleSave = () => {
        const name = nameRef.current.value;
        const price = priceRef.current.value;
        const brand = brandRef.current.value;
        const stock = stockRef.current.value;
        const category = categoryRef.current.value;
        const description = descriptionRef.current.value;
        const image = imgRef.current.value;
        axios.post("http://localhost:9090/products",{
            name: name,
            price: price,
            brand: brand,
            stock: stock,
            category: category,
            description: description
        }).then((response) => {
            console.log(response);
            alert("Product created successfully");
            window.location.href = "/products";
        }).catch((error) => {
            console.log(error);
            alert("Something went wrong, please check the information and try again.")
        });
    };
    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>Product {props.name}</h1>
                    <Divider />
                    <h2>Product name</h2>    
                    <TextField id="outlined-basic" variant="outlined" size="small" value={props.name} inputRef={nameRef}/>
                    <h2>Price</h2>
                    <TextField id="outlined-basic" variant="outlined" size="small" value={props.price} inputRef={priceRef}/>
                    <h2>Brand</h2>
                    <TextField id="outlined-basic" variant="outlined" size="small" value={props.brand} inputRef={brandRef}/>
                    <h2>Initial stock</h2>
                    <TextField id="outlined-basic" variant="outlined" size="small" value={props.stock} inputRef={stockRef}/>
                    <h2>Image URL</h2>
                    <TextField id="outlined-basic" variant="outlined" size="small" inputRef={stockRef}/>
                    <h2>Category</h2>
                    <Autocomplete
                        disablePortal
                        id="combo-box-demo"
                        options={options}
                        sx={{ width: 300 }}
                        renderInput={(params) => <TextField {...params} size="small" inputRef={categoryRef} value={props.category}/>}
                    />
                    <h2>Description</h2>
                    <TextField id="outlined-basic"  variant="outlined" multiline rows={3} size="small" value={props.description} inputRef={descriptionRef}/>
                    <div className={styles.buttonsNewProduct}> 
                        <Button variant="contained" color="success" onClick={handleSave}>Save</Button>
                        <div style={{width:30}}></div>
                        <Button variant="contained" color="error" onClick={handleCancel}>Cancel</Button>
                    </div>
                </div>
            </div>
        </div>
    )
}

/**export async function getServerSideProps(context:any) {
    const {data} = await axios.get("http://localhost:9090/item/"+context.query.productId,{
        headers:{
            "Authorization":"Bearer " + localStorage.getItem("token"),
            "Content-Type":"application/json"
        }
    })
    return {
        props: {
            name: data.name,
            price: data.price,
            category: data.category,
            brand: data.brand,
            stock: data.stock,
            imageUrl: data.imageURL,
            description: data.description
        },
    };
}**/