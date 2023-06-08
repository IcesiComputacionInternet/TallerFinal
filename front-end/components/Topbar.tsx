import styles from '../styles/Topbar.module.css'
import myLogo from "../resources/logo.png";

export default function Topbar(){
    return(
        <div className={styles.bar}>
            <div className={styles.buttonsSection}>
                
            </div>
            <div className={styles.logoSection}>
                <img src={myLogo.src} alt="Logo" style={{width:70,height:70}}/>
                <h2>E-Shop</h2>
            </div>
            <div className={styles.userButtons}>

            </div>
            
        </div>
    )
}