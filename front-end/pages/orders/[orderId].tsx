import { Divider } from "@mui/material";
import styles from "../../styles/Products.module.css";
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import axios from "axios";
import { useRef } from "react";

const options = [{ label: 'Created'},{label:'In progress'}, {label:'Completed'}, {label:'Cancelled'} ];

export default function OrderScreen(props:any) {
    const stateRef = useRef();
    const totalRef = useRef();
    const customerFirstNameRef = useRef();
    const customerAddressRef = useRef();


    const handleCancel = () => {
        window.location.href = "/orders";
    };
    const handleSave = () => {
        alert("Order updated successfully");
    };
    return (
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>Order #{props.id}</h1>
                    <Divider />
                    <h2>Order state</h2>
                    <Autocomplete
                        disablePortal
                        id="combo-box-demo"
                        options={options}
                        sx={{ width: 300 }}
                        renderInput={(params) => <TextField {...params} size="small"  value={props.category}/>}
                    />
                    <h2>Total</h2>
                    <TextField id="outlined-basic" variant="outlined" size="small" value={props.total}/>
                    <h2>Customer</h2>
                    <h4>{props.customerFirstNameRef}</h4>
                    <h4>{props.customerAddressRef}</h4>
                    <h4>{props.customerPhoneNumber}</h4>
                    <h2>Details</h2>
                    <h4>{props.items}</h4>
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

/**export async function getServerSideProps(context:any){
    const {data} = await axios.get("http://localhost:9090/salesOrder/getOrder/"+context.query.orderId, {
        headers:{
            "Authorization":"Bearer " + localStorage.getItem("token"),
            "Content-Type":"application/json"
        }
    })
    return{
        props:{
            id: data.id,
            state: data.state,
            total: data.total,
            customerFirstName: data.customer.firstName,
            customerAddress: data.customer.address,
            customerPhoneNumber: data.customer.phoneNumber,
            items: data.items
        }
    }
}**/