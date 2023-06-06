import styles from "../styles/Products.module.css";

export default function NewProduct(){
    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainer}>
                <div className={styles.formSection}>
                    <h1>New Product</h1>
                </div>
            </div>
        </div>
    )
}