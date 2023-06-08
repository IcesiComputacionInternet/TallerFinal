import styles from "../styles/User.module.css";

export default function UserView() {
    return (
        <div className={styles.maxContainer}>
            <div className={styles.promotionBanner}>
                    <h1 style={{color:"#FFFFFF"}}>Up to 50% discount in your new TV!</h1>
            </div>
        </div>
    );
}
