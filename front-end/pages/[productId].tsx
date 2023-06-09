import styles from "../styles/User.module.css";
import Divider from '@mui/material/Divider';
import { useEffect, useState } from "react";
import axios from "axios";
import { Button } from "@mui/material";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import CreditCardIcon from '@mui/icons-material/CreditCard';
import PaymentsIcon from '@mui/icons-material/Payments';


export default function Product(props:any){
    const [product,setProduct] = useState();
    const [loading,setLoading] = useState(true);
    const [open, setOpen] = useState(false);
        useEffect(() => {
            axios.get("http://localhost:9090/item/itemId/"+props.productId, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                }
            }).then((response) => {
                console.log(response);
                setProduct(response.data);
                setLoading(false);
            }).catch((error) => {
                console.log(error);
            });
        },[]);

    const handleAgree = () => {
        axios.post("http://localhost:9090/salesOrder", {
            "customer": localStorage.getItem("userId"),
            "items": [
                {
                    "name": product.name,
                    "quantity": product.stock,
                    "price": product.price,
                    "category": product.category.name,
                    "brand": product.brand
                }
            ]
        }, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("token")}`,
            }
        }).then((response) => {
            console.log(response);
            alert("Order created successfully,we will contact you soon");
            setOpen(false);
            window.location.href = "/products/showProducts";
        }).catch((error) => {
            console.log(error);
        });
        setOpen(true);
    }
    const handleDisagree = () => {
        setOpen(false);
    }
    const handleBuy = () => {
        setOpen(false);
    }

    if(loading){
        return(
            <div className={styles.customLoader}></div>
        )
    }else{
        return(
            <div className={styles.maxContainer}>
                <div className={styles.insideContainerRow}>
                    <div className={styles.productInfoSection}>
                        <div >
                            <div className={styles.productImgSection}>
                                <img src={product.imageURL} 
                                alt="product"
                                className={styles.productImg}/>
                            </div>
                        </div>
                        <div className={styles.productDescription}>
                            <h3>Product characteristics</h3>
                            <div style={{display:"flex",width:"calc(95%)",margin:15,flexGrow:1}}>
                                <h4 style={{fontWeight:"normal"}}>{product.description}</h4>
                            </div>
                        </div>
                    </div>
                    <div className={styles.productPaymentSection}>
                        <h2>{product.name}</h2>
                        <div style={{display:"flex",justifyContent:"left",width:"calc(100%)",flexDirection:"column",flexGrow:1}}>
                            <h3>Price: ${product.price}</h3>
                            <h3>Remaining unities: {product.stock}</h3>
                            <h3>Category: {product.category.name}</h3>
                        </div>
                        <div style={{display:"flex",justifyContent:"left",width:"calc(100%)",flexDirection:"column",flexGrow:1}}>

                            <Button variant="contained"
                            style={{backgroundColor:"#21897E",color:"white",margin:10}}
                            onClick={handleBuy}>
                                Buy
                            </Button>
                        </div>
                        
                    </div>
                </div>
                <Dialog
                    open={open}
                    onClose={() => console.log("")}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                <DialogTitle id="alert-dialog-title">
                {"Just one step more!"}
                </DialogTitle>
                <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    You are one step closer to having your brand new {product.name}!
                    Select your payment method and we will take care of the rest.
                </DialogContentText>
                <div style={{display:"flex",flexDirection:"row",marginTop:20,justifyContent:"center"}}>
                    <Button variant="contained"
                    endIcon={<CreditCardIcon/>}
                    onClick={handleAgree}
                    >Credit Card</Button>
                    <Button variant="contained"
                    endIcon={<PaymentsIcon/>}
                    style={{marginLeft:10}}
                    onClick={handleAgree}
                    >Cash</Button>
                </div>
                </DialogContent>
                <DialogActions>
                <Button onClick={handleDisagree}>Cancel</Button>
                </DialogActions>
                </Dialog>
            </div>
        )
    }
}

export function getServerSideProps(context:any){
    return{
        props:{
            productId:context.params.productId
        }
    }
}
