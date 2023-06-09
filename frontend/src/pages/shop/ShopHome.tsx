import { useEffect, useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
import ShopNavbar from "../../components/ShopNavbar";

const baseUrl = "http://localhost:8091";

function ShopHome (){

  const [currentUser, setCurrentUser] = useState("");

  const navigation : NavigateFunction = useNavigate();

  const [orders, setOrders] = useState([]);

  useEffect(() => {

    if(localStorage.getItem("jwt")){
      const user = localStorage.getItem("currentRole");

      if(user){
        setCurrentUser(user)
      }
    }else{
      navigation("/NotFound");
    }

    async function getData() {

      const resultOrders = await getOrders();
      setOrders(resultOrders);
    }

    getData();
  }, []);


  if(currentUser !== "SHOP"){
    navigation("/NotFound");
  }

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


