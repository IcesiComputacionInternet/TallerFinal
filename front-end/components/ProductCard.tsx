import styles from "../styles/User.module.css";

interface ProductCardProps{
    name: string;
    price: string;
    productId: string;
    imageUrl: string;
}

export default function ProductCard({name,price,productId,imageUrl}:ProductCardProps){
    const handleClick = () => {
        window.location.href = "/"+productId;
    }
    return(
        <div className={styles.cardDiv} onClick={handleClick}>
            <div className={styles.imageSection}>
                <img src={imageUrl} alt="product image" style={{height:150,objectFit:"cover",width:250}}/>
            </div>
            <div className={styles.infoSection}>
                <h3>{name}</h3>
                <h4>${price}</h4>
            </div>
        </div>
    )
}