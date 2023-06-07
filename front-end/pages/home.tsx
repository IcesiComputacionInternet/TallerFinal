import { use, useEffect,useState } from "react";
import styles from '../styles/Home.module.css'
import AdminView from '../components/AdminView'
import ShopView from '../components/ShopView'
export default function Home() {
    const [role, setRole] = useState<String | null>('none');
    useEffect(() => {
        const role = localStorage.getItem('role');
        setRole(role);
        if(role === 'none' || role === null) {
            console.log('role is null')
        }else{
            console.log(role)
        }
    }, []) 
    if(role === 'admin') {
        return (
        <AdminView/>
        )
    }else if(role === 'user') {
        return (
            <div>
                <h1>User</h1>
            </div>
            )
    }else if(role === 'shop') {
        return (
            <ShopView/>
            )
    }else{
        return (
            <div className={styles.customLoader}></div>
        )
    }
    
}
