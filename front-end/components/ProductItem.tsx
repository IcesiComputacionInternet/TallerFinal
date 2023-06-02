import { IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { createTheme, ThemeProvider } from '@mui/material/styles';

interface ProductItemProps {
    name: String;
    price: Number;
    amount: Number;
    image: String;
    category: String;
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
            <div style={{flexGrow:2,borderRight:"1px solid black"}}>
                <h3 style={{fontWeight:"normal"}}>{props.name}</h3>
            </div>
            <div style={{flexGrow:2,borderRight:"1px solid black",paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.price + "$"}</h3>
            </div>
            <div style={{flexGrow:2,borderRight:"1px solid black",paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.amount + " left"}</h3>
            </div>
            <div style={{flexGrow:2,paddingLeft:10}}>
                <h3 style={{fontWeight:"normal"}}>{props.category}</h3>    
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