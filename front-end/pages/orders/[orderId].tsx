import { Divider } from "@mui/material";
import styles from "../../styles/Products.module.css";
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import axios from "axios";
import { useRef,useState,useEffect } from "react";

const options = [{ label: 'CREATED'},{label:'IN_PROGRESS'}, {label:'COMPLETED'}, {label:'CANCELLED'} ];

export default function OrderScreen(props:any) {
    const [order,setOrder] = useState();
    console.log(props.id);
    console.log(order)
    useEffect(() => {
        axios.get(`http://localhost:9090/salesOrder/getOrder/${props.id}`,{
            headers:{
                "Authorization":"Bearer " + localStorage.getItem("token"),
                "Content-Type":"application/json"
            }
        }).then((response) => {
            console.log(response.data);
            setOrder(response.data);
            console.log(order)
        }).catch((error) => {
            console.log(error);
        })
    },[])
    

    const stateRef = useRef();
    const totalRef = useRef();
    const customerFirstNameRef = useRef();
    const customerAddressRef = useRef();


    const handleCancel = () => {
        window.location.href = "/orders";
    };
    const handleSave = () => {
        axios.patch(`http://localhost:9090/salesOrder/updateState`,{
            "orderId":order.orderId,
            "status":stateRef.current.value
        },{
            headers:{
                "Authorization":"Bearer " + localStorage.getItem("token"),
                "Content-Type":"application/json"
            }
        }).then((response) => {
            console.log(response.data);
            alert("Order updated successfully");
            window.location.href = "/orders";
        }).catch((error) => {
            console.log(error);
        })

    };
    if(!order){
        return (<div className={styles.customLoader}></div>)
    }else{
    return (
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>Order #{props.id}</h1>
                    <Divider />
                    <h3>Previous status: {order.status}</h3>
                    <h2>New status</h2>
                    <Autocomplete
                        disablePortal
                        id="combo-box-demo"
                        options={options}
                        sx={{ width: 300 }}
                        renderInput={(params) => <TextField {...params} size="small"  inputRef={stateRef}/>}
                    />
                    <h2>Total</h2>
                    <TextField id="outlined-basic" variant="outlined" size="small" value={order.total}/>
                    <h2>Customer</h2>
                    <h4 style={{fontWeight:"normal",margin:1}}>{order.customer.firstName}</h4>
                    <h4 style={{fontWeight:"normal",margin:1}}>{order.customer.address}</h4>
                    <h4 style={{fontWeight:"normal",margin:1}}>{order.customer.phoneNumber}</h4>
                    <h2>Details</h2>
                    {order.items.map((item:any) => {
                        return(
                            <div>
                                <h4 style={{fontWeight:"normal",margin:1}}>{item.name}</h4>
                                <h4 style={{fontWeight:"normal",margin:1}}>{item.price}</h4>
                            </div>
                        )
                    })}
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

export async function getServerSideProps(context:any){
    return {
        props:{
            id:context.params.orderId,
        }
    }
}