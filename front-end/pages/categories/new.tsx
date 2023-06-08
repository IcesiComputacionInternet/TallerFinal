import { Divider, TextField } from '@mui/material'
import styles from '../../styles/Products.module.css'
import { Button } from '@mui/material'

export default function NewCategory(){
    const handleCancel = () => {
        window.location.href = "/categories";
    };

    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>New Category</h1>
                    <Divider />
                    <h2>Category name</h2>
                    <TextField id="outlined-basic" label="Name" variant="outlined" size="small"/>
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