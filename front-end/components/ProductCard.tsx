import styles from "../styles/User.module.css";

interface ProductCardProps{
    name: string;
    price: string;
    handleClick: any;
}

export default function ProductCard({name,price,handleClick}:ProductCardProps){
    return(
        <div className={styles.cardDiv} onClick={handleClick}>
            <div className={styles.imageSection}></div>
            <div className={styles.infoSection}>
                <h3>{name}</h3>
                <h4>{price}</h4>
            </div>
        </div>
    )
}