import styles from "../../styles/Admin.module.css";

export default function UserItem() {
    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.topBar}>
                    <h1 style={{marginRight:30}}>User</h1>
                </div>
            </div>
        </div>
    )
}