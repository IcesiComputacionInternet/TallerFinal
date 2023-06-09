import styles from "../../styles/Admin.module.css";
import UserItem from "../../components/UserItem";
import {useState,useEffect} from "react";

export default function Users(){
    const [users,setUsers] = useState([]);
    

    return(
        <div className={styles.maxContainer} >
            <div className={styles.insideContainer}>
                <div className={styles.topBar}>
                    <h1 style={{marginRight:30}}>Users List</h1>
                </div>
                <div className={styles.itemsSection}>
                    <div className={styles.itemsList}>
                        <UserItem id="1" firstName="User 1" lastName="Last Name 1" email="example@email.com" phone="123" birthday="" orders="" address=""/>	

                    </div>
                </div>
            </div>
        </div>
    )
}