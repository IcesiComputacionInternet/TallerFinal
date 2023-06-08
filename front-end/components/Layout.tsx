import styles from '../styles/Layout.module.css'
import Sidebar from '../components/Sidebar'
import Topbar from '../components/Topbar'
import { useState,useEffect } from 'react';

function Layout({children}:any){
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
        <div className={styles.layout}>
            <Sidebar role="admin"/>
            {children}
        </div>
    )}else if(role === 'user') {
        return (
            <div className={styles.userLayout}>
                <Topbar />
                {children}
            </div>
            )
    }else if(role === 'shop') {
        return (
            <div className={styles.layout}>
                <Sidebar role="shop"/>
                {children}
            </div>
        )
    }else{
        return (
        <div className={styles.layout}>
            {children}
        </div>
        )
    }
}

export default Layout;