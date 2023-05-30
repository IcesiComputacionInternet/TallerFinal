import { NextPage } from "next";
import myLogo from "../resources/logo.png"
import SideButton from "./SideButton";

const items  = [
    {
        name:"Home",
        path:"/home"
    },
    {
        name:"Products",
        path:"/products"
    },
    {
        name:"Users",
        path:"/users"
    },
    {
        name:"Orders",
        path:"/orders"
    },
    {
        name:"Categories",
        path:"/categories"
    }];


const Sidebar : NextPage = () => {
    return (
        <div style={{display:"flex",height:"calc(100%)",minWidth:200,backgroundColor:"#595959",flexDirection:"column"}}>
            <div className="logo" style={{display:"flex", alignItems:"center",justifyContent:"center",backgroundColor:"white"}}>
                <img src={myLogo.src} alt="Logo" style={{width:90,height:90}}/>
                <h2>E-Shop</h2>
            </div>
            <div className="buttons">
                <SideButton name="Home" path="/" icon={null}></SideButton>

            </div>
            <h1>Sidebar</h1>
        </div>
    );
}

export default Sidebar;