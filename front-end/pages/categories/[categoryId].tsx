import styles from '../../styles/Products.module.css'
import { Divider } from '@mui/material'
import TextField from '@mui/material/TextField'
import { Button } from '@mui/material'
import axios from 'axios';
import { useRef } from 'react';


export default function Category(props: any) {
    const nameRef = useRef();
    const descriptionRef = useRef();
    const handleCancel = () => {
        window.location.href = "/categories";
    };
    const handleSave = () => {
        axios.post("http://localhost:9090/category/category", {
            name: nameRef.current.value,
            description: descriptionRef.current.value
        }).then(res => {
            alert("Category created successfully");
            window.location.href = "/categories";
        }).catch(err => {
            alert(err);
        })
    }
    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>Category</h1>
                    <Divider/>
                    <h2>Category name</h2>
                    <TextField id="outlined-basic"  variant="outlined" size="small" value={props.name} inputRef={nameRef}/>
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

/**export async function getServerSideProps(context:any){
    const data = await axios.get("http://localhost:9090/item/",{
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