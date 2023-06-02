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
        name:"Orders",
        path:"/orders",
        icon:<ListAltIcon sx={{color:"#e6e6e9"}}/>,
        onClick:() => {
            Router.push("/orders");
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


const Sidebar : NextPage = () => {
    const handleLogout = () => {
        localStorage.clear();
        window.location.href = "/";
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

export default Sidebar;