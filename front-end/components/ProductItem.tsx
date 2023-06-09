import { IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import React from "react";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';

interface ProductItemProps {
    name: String;
    price: Number;
    amount: Number;
    category: String;
    productId: String;
    handleAgree: () => void;
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

export default function ProductItem(props: ProductItemProps) {
    const [open, setOpen] = React.useState(false);
    const handleDelete = () => {
        setOpen(true);
    }
    const handleDisagree = () => {
        setOpen(false);
    }
    const handleEdit = () => {
        window.location.href = "/products/" + props.productId;
    }
    return (
        <div style={{
            display:"flex",
            flexDirection:"row",
            borderRadius: 10,
            backgroundColor:"#ffffff",
            boxShadow: "1px 1px 5px #000000",
            marginBottom:15,
            height:60,
            paddingLeft:20,
            paddingRight:20,
        }} >
            <div style={{width:350,borderRight:"1px solid black"}}>
                <h3 style={{fontWeight:"normal"}}>{props.name}</h3>
            </div>
            <div style={{width:200,borderRight:"1px solid black",paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.price + "$"}</h3>
            </div>
            <div style={{width:90,borderRight:"1px solid black",paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.amount + " left"}</h3>
            </div>
            <div style={{flexGrow:2,paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.category}</h3>    
            </div>
            <div style={{flexGrow:1,display:"flex",justifyContent:"flex-end"}}>
                <ThemeProvider theme={theme}>
                <IconButton onClick={handleDelete}>
                    <DeleteIcon color="secondary"/>
                </IconButton>
                <IconButton onClick={handleEdit}>
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
                {"Are you sure you want to delete this product?"}
                </DialogTitle>
                <DialogContent>
                <DialogContentText id="alert-dialog-description">
                    All the information will be erased from the database and the product will not be able to be selled again.
                </DialogContentText>
                </DialogContent>
                <DialogActions>
                <Button onClick={handleDisagree}>Disagree</Button>
                <Button onClick={props.handleAgree} autoFocus>
                    Agree
                </Button>
                </DialogActions>
            </Dialog>
        </div>   
    )
}