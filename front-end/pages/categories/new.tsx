import { Divider, TextField } from '@mui/material'
import styles from '../../styles/Products.module.css'
import { Button } from '@mui/material'
import axios from 'axios';
import { useRef } from 'react';

export default function NewCategory(){
    const nameRef = useRef();
    const descriptionRef = useRef();
    const handleCancel = () => {
        window.location.href = "/categories";
    };
    const handleSave = () => {
        axios.post("http://localhost:9090/category", {
            name: nameRef.current.value,
            description: descriptionRef.current.value
        },{
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                
            }
        }).then(res => {
            alert("Category created successfully");
            window.location.href = "/categories";
        }).catch(err => {
            alert("Error creating category");
        }
        )
    }

    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>New Category</h1>
                    <Divider />
                    <h2>Category name</h2>
                    <TextField id="outlined-basic"  variant="outlined" size="small" inputRef={nameRef}/>
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