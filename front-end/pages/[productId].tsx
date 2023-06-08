import styles from "../styles/User.module.css";
import Divider from '@mui/material/Divider';

export default function Product(props:any){
    console.log(props)
    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainerRow}>
                <div className={styles.productInfoSection}>
                    <div >
                        <div className={styles.productImgSection}>
                            <img src="https://img.freepik.com/free-psd/helmet-mock-up_1310-159.jpg?w=1060&t=st=1686239528~exp=1686240128~hmac=64e1bfc487d13dc3f516ed6bcbbdd4ddd1a8528957c7d7d22339cc9562ac1c6a" 
                            alt="product"
                            className={styles.productImg}/>
                        </div>
                    </div>
                    <div className={styles.productDescription}>
                        <h3>Caracteristicas del producto</h3>
                        <div style={{display:"flex",width:"calc(95%)",margin:15,flexGrow:1}}>
                            <h4 style={{fontWeight:"normal"}}>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</h4>
                        </div>
                    </div>
                </div>
                <div className={styles.productPaymentSection}>
                    <h2>Xiaomi TV 4K</h2>
                    <div style={{display:"flex",justifyContent:"left",width:"calc(100%)",border:"1px solid red"}}>
                        <h3>$1'500.000</h3>
                    </div>
                    
                </div>
            </div>
            
        </div>
    )
}

export async function getServerSideProps(context:any){
    const {productId} = context.query;
    return{
        props:{
            productId
        }
    }
}