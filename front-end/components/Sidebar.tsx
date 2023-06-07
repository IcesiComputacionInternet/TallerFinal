import { NextPage } from "next";
import myLogo from "../resources/logo.png";
import SideButton from "./SideButton";
import InventoryIcon from '@mui/icons-material/Inventory';
import { Divider } from "@mui/material";
import styles from "../styles/Sidebar.module.css";
import HomeIcon from '@mui/icons-material/Home';
import CategoryIcon from '@mui/icons-material/Category';
import PeopleIcon from '@mui/icons-material/People';
import ListAltIcon from '@mui/icons-material/ListAlt';
import LogoutIcon from '@mui/icons-material/Logout';
import Router from "next/router";

interface SideBarProps {
    role:String | null;
}

const items  = [
    {
        name:"Home",
        path:"/home",
        icon:<HomeIcon sx={{color:"#e6e6e9"}}/>,
        onClick:() => {
            Router.push("/home");
        }
    },
    {
        name:"Products",
        path:"/products",
        icon:<InventoryIcon sx={{color:"#e6e6e9"}}/>,
        onClick:() => {
            Router.push("/products");
        }
    }
    ,{
        name:"Orders",
        path:"/orders",
        icon:<ListAltIcon sx={{color:"#e6e6e9"}}/>,
        onClick:() => {
            Router.push("/orders");
        }
    },
    {
        name:"Users",
        path:"/users",
        icon:<PeopleIcon sx={{color:"#e6e6e9"}}/>,
        onClick:() => {
            Router.push("/users");
        }
    },
    
    {
        name:"Categories",
        path:"/categories",
        icon:<CategoryIcon sx={{color:"#e6e6e9"}}/>,
        onClick:() => {
            Router.push("/categories");
        }
    }];


export default function Sidebar({role}:SideBarProps) {
    const handleLogout = () => {
        localStorage.clear();
        window.location.href = "/";
    }

    if(role === 'shop') {
        items.splice(3,2)
    }

    return (
        <div className={styles.bar}>
            <div className={styles.logoContainer} >
                <img src={myLogo.src} alt="Logo" style={{width:90,height:90}}/>
                <h2 className={styles.logoTitle}>E-Shop</h2>
            </div>
            <div className={styles.buttonsContainer}>
                <Divider sx={{backgroundColor:"#e6e6e9"}}/>
                {items.map((item,index) => {
                    return (
                        <SideButton key={index} name={item.name} path={item.path} icon={item.icon} handleClick={item.onClick}/>
                    )
                })}
            </div>
            <div className={styles.logoutContainer}>
                    <SideButton name="Logout" path="/" icon={<LogoutIcon sx={{color:"#e6e6e9"}}/>} handleClick={handleLogout}/>
            </div> 
        </div>
    );
}

