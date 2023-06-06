import { IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import styles from "../styles/Admin.module.css";

interface UserItemProps {
    firstName: String;
    lastName: String;
    email: String;
    phone: String;
    address: String;
    birthday: String;
    orders: any;
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
                <IconButton>
                    <DeleteIcon color="secondary"/>
                </IconButton>
                <IconButton>
                    <EditIcon color="secondary"/>
                </IconButton>
                </ThemeProvider>
            </div>
        </div>
    )
}
