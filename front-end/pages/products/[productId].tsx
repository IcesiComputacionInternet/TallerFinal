import styles from "../../styles/Products.module.css";
import Divider from "@mui/material/Divider";
import { Button, TextField } from "@mui/material";
import Autocomplete from '@mui/material/Autocomplete';
import axios from "axios";
import { useRef,useEffect,useState } from "react";




export default function ProductItem(props:any) {
    const [product,setProduct] = useState();
    const [categories,setCategories] = useState();
    const [productLoaded,setProductLoaded] = useState(false);
    const [categoriesLoaded,setCategoriesLoaded] = useState(false);
    useEffect(() => {
        axios.get(`http://localhost:9090/item/itemId/${props.productId}`,{
            headers:{
                "Authorization":"Bearer " + localStorage.getItem("token"),
                "Content-Type":"application/json"
            }
        }).then((response) => {
            console.log(response.data);
            setProduct(response.data);
            setProductLoaded(true);
        }).catch((error) => {
            console.log(error);
        })
    },[])

    useEffect(() => {
        axios.get(`http://localhost:9090/category/all`,{
            headers:{
                "Authorization":"Bearer " + localStorage.getItem("token"),
                "Content-Type":"application/json"
            }
        }).then((response) => {
            console.log(response.data);
            setCategories(response.data.map((category:any) => {
                return {label: category.name, id: category.id};
            }));
            
            setCategoriesLoaded(true);
        }).catch((error) => {
            console.log(error);
        })
    },[])

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

    if(!productLoaded || !categoriesLoaded){
        return <div>Loading...</div>
    }
    else{
    return(
            <div className={styles.maxContainer}>
                <div className={styles.insideContainer}>
                    <div className={styles.formSection}>
                        <h1>Product {props.name}</h1>
                        <Divider />
                        <h2>Product name</h2>    
                        <TextField id="outlined-basic" variant="outlined" size="small" value={product.name} inputRef={nameRef}/>
                        <h2>Price</h2>
                        <TextField id="outlined-basic" variant="outlined" size="small" value={product.price} inputRef={priceRef}/>
                        <h2>Brand</h2>
                        <TextField id="outlined-basic" variant="outlined" size="small" value={product.brand} inputRef={brandRef}/>
                        <h2>Initial stock</h2>
                        <TextField id="outlined-basic" variant="outlined" size="small" value={product.stock} inputRef={stockRef}/>
                        <h2>Image URL</h2>
                        <TextField id="outlined-basic" variant="outlined" size="small" value={product.imageURL} inputRef={stockRef}/>
                        <h2>Category</h2>
                        <Autocomplete
                            disablePortal
                            id="combo-box-demo"
                            options={categories}
                            sx={{ width: 300 }}
                            value={product.category.name}
                            renderInput={(params) => <TextField {...params} size="small" inputRef={categoryRef}/>}
                        />
                        <h2>Description</h2>
                        <TextField id="outlined-basic"  variant="outlined" multiline rows={3} size="small" value={product.description} inputRef={descriptionRef}/>
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
    
}

export async function getServerSideProps(context:any) {
    return {
        props: {
            productId: context.params.productId,
        },
    };
}