import styles from "../../styles/Products.module.css";
import { Button, Divider } from "@mui/material";
import {TextField} from "@mui/material";
import Autocomplete from '@mui/material/Autocomplete';
import axios from "axios";
import { useRef,useState,useEffect } from "react";

const options = [{ label: 'The Shawshank Redemption', year: 1994 },{label:'Hey',year:1886} ];

export default function NewProduct(){
    const [categories,setCategories] = useState([]);
        useEffect(() => {
            axios.get("http://localhost:9090/category/all",{
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                }
            }).then((response) => {
                setCategories(response.data.map((category:any) => {
                    return {label: category.name, id: category.id};
                }));
                console.log(categories);
            }).catch((error) => {
                console.log(error);
            });
        },[]);


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
        axios.post("http://localhost:9090/item",{
            name: name,
            price: price,
            brand: brand,
            stock: stock,
            category: category,
            description: description,
            imageURL:image
        },{
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("token")}`,
            }
        }).then((response) => {
            console.log(response.data);
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
                    <h1>New Product</h1>
                    <Divider />
                    <h2>Product name</h2>    
                    <TextField id="outlined-basic"  variant="outlined" size="small" inputRef={nameRef}/>
                    <h2>Price</h2>
                    <TextField id="outlined-basic"  variant="outlined" size="small" inputRef={priceRef}/>
                    <h2>Brand</h2>
                    <TextField id="outlined-basic"  variant="outlined" size="small" inputRef={brandRef}/>
                    <h2>Initial stock</h2>
                    <TextField id="outlined-basic"  variant="outlined" size="small" inputRef={stockRef}/>
                    <h2>Image URL</h2>
                    <TextField id="outlined-basic"  variant="outlined" size="small" inputRef={imgRef}/>
                    <h2>Category</h2>
                    <Autocomplete
                        disablePortal
                        id="combo-box-demo"
                        options={categories}
                        sx={{ width: 300 }}
                        renderInput={(params) => <TextField {...params} size="small"  inputRef={categoryRef}/>}
                    />
                    <h2>Description</h2>
                    <TextField id="outlined-basic"  variant="outlined" multiline rows={3} size="small" inputRef={descriptionRef}/>
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