import { NextPage } from "next";
import styles from "../styles/Admin.module.css";
import Sidebar from "../components/Sidebar"


const AdminView : NextPage = () => {
    return (
        <div className={styles.maxContainer}>
            <div className={styles.insideHomeContainer}>
                <div className={styles.buttonsContainer}>

                </div>
            </div>
        </div>
    );
}

export default AdminView;
