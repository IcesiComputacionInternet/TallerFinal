import ProductCard from "../../components/ProductCard";
import styles from "../../styles/User.module.css";
import Filterbar from "../../components/Filterbar";

const items = [{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},{
    name: "Xiaomi TV 60\" 4K",
    price: "$1'500.000",
    handleClick: () => {
        window.location.href = "/1";
    }
},]

//Made with ChatGPT
const createProductCardDivs = (items:any) => {
    const divs:any = [];
    let currentDiv:any = [];
  
    items.forEach((item:any, index:any) => {
      currentDiv.push(<ProductCard key={index} name={item.name} price={item.price} handleClick={item.handleClick}/>);
  
      if (currentDiv.length === 4 || index === items.length - 1) {
        divs.push(<div className={styles.cardGroup} key={divs.length}>{currentDiv}</div>);
        currentDiv = [];
      }
    });
  
    return divs;
  };


  
export default function ProducsList(){
    return(
        <div className={styles.maxContainer}>
            <div className={styles.insideContainerRow}>
                <Filterbar/>
                <div className={styles.productsList}>
                    {createProductCardDivs(items)}
                </div>
            </div>
            
        </div>
    )
}