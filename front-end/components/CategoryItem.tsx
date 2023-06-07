import { IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import styles from "../styles/Admin.module.css";
import React from "react";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';

interface CategoryItemProps {
    name : String;
    description : String;
}


const theme = createTheme({
    palette: {
        primary: {
            main: "#21897E",
        },
        secondary: {
            main: "#3BA99C",
        }
    },
});


export default function CategoryItem(props: CategoryItemProps){
    const [open, setOpen] = React.useState(false);
    return(
        <div className={styles.itemDiv}>
            <div style={{flexGrow:2,borderRight:"1px solid black"}}>
                <h3 style={{fontWeight:"normal",marginLeft:5}}>{props.name}</h3>
            </div>
            <div style={{flexGrow:2,borderRight:"1px solid black",paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.description}</h3>
            </div>
            <div style={{flexGrow:1,display:"flex",justifyContent:"flex-end"}}>
                <ThemeProvider theme={theme}>
                    <IconButton onClick={() => console.log("")}>
                        <DeleteIcon color="secondary"/>
                    </IconButton>
                    <IconButton onClick={() => console.log("")}>
                        <EditIcon color="secondary"/>
                    </IconButton>
                </ThemeProvider>
            </div>
            <Dialog
                open={open}
                onClose={() => console.log("")}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
                >
                <DialogTitle id="alert-dialog-title">
                {"Are you sure you want to delete this user?"}
                </DialogTitle>
                <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    All the information will be erased from the database and the user will not be able to log in again.
                </DialogContentText>
                </DialogContent>
                <DialogActions>
                <Button onClick={() => console.log("")}>Disagree</Button>
                <Button onClick={() => console.log("")} autoFocus>
                    Agree
                </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}