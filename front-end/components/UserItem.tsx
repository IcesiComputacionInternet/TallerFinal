import { IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import styles from "../styles/Admin.module.css";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import React from "react";

interface UserItemProps {
    firstName: String;
    lastName: String;
    email: String;
    phone: String;
    address: String;
    birthday: String;
    orders: any;
    id: any;
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

export default function UserItem(props: UserItemProps) {
    const [open, setOpen] = React.useState(false);
    const handleOnClick = () => {
        window.location.href = "/users/" + props.id;
    }

    const handleClose = () => {
        setOpen(false);
    };

    const handleDelete = () => {
        setOpen(true);
    };

    const handleAgree = () => {
        //deleteUser
    }

    const handleDisagree = () => {
        setOpen(false);
    }

    return(
        <div className={styles.itemDiv}>
            <div style={{flexGrow:2,borderRight:"1px solid black"}}>
                <h3 style={{fontWeight:"normal",marginLeft:5}}>{props.firstName}</h3>
            </div>
            <div style={{flexGrow:2,borderRight:"1px solid black",paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.lastName}</h3>
            </div>
            <div style={{flexGrow:2,paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.email}</h3>
            </div>
            <div style={{flexGrow:1,display:"flex",justifyContent:"flex-end"}}>
                <ThemeProvider theme={theme}>
                <IconButton onClick={handleDelete}>
                    <DeleteIcon color="secondary"/>
                </IconButton>
                <IconButton onClick={handleOnClick}>
                    <EditIcon color="secondary"/>
                </IconButton>
                </ThemeProvider>
            </div>
                <Dialog
                open={open}
                onClose={handleClose}
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
                <Button onClick={handleDisagree}>Disagree</Button>
                <Button onClick={handleClose} autoFocus>
                    Agree
                </Button>
                </DialogActions>
                </Dialog>
        </div>
    )
}
