import { NextPage } from "next";
import styles from "../styles/Admin.module.css";
import Sidebar from "../components/Sidebar"


const AdminView : NextPage = () => {
    return (
        <div className={styles.maxContainer}>
            <h1>Admin View</h1>
        </div>
    );
}

export default AdminView;
