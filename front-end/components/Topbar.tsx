import styles from '../styles/Topbar.module.css'
import myLogo from "../resources/logo.png";
import TopbarButton from './TopbarButton';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import IconButton from '@mui/material/IconButton';

export default function Topbar(){
    const handleLogout = () => {
        localStorage.clear();
        window.location.href = '/';
    }

    const handleHome = () => {
        window.location.href = '/home';
    }

    const handleProducts = () => {
        window.location.href = '/products/showProducts';
    }


    return(
        <div className={styles.bar}>
            <div className={styles.buttonsSection}>
                <TopbarButton text="Home" handleClick={handleHome}/>
                <TopbarButton text="Products" handleClick={handleProducts}/>
            </div>
            <div className={styles.logoSection}>
                <img src={myLogo.src} alt="Logo" style={{width:70,height:70}}/>
                <h2 style={{color:"#FFFFFF"}}>E-Shop</h2>
            </div>
            <div className={styles.userButtons}>
                <TopbarButton text="Logout" handleClick={handleLogout}/>
                <IconButton style={{color:"#FFFFFF"}} aria-label="cart" size="large">
                    <AccountCircleIcon/>
                </IconButton>
            </div>
            
        </div>
    )
}