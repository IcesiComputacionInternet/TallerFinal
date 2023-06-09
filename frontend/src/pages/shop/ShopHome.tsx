import { useEffect, useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
import ShopNavbar from "../../components/ShopNavbar";

const baseUrl = "http://localhost:8091";

function ShopHome (){

  const navigation : NavigateFunction = useNavigate();

  const [orders, setOrders] = useState([]);

  useEffect(() => {
    async function getData() {

      const resultOrders = await getOrders();
      setOrders(resultOrders);
    }

    getData();
  }, []);

  const handleClickMoreOrders = async (event: any) => {
    event.preventDefault();
    navigation("/admin/orders");
  };

  return (
    <>
     <ShopNavbar />
     
    </>
  );
      
}


async function getOrders(){

  const orders = await axios.get(
    baseUrl+"/orders",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )

  return orders.data.slice(0,5);
}

    
export default ShopHome;


