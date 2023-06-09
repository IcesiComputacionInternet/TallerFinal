import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navigation from '../../components/Navigation'


export default function order() {

    const router = useRouter();

    const [categories, setCategories] = useState([]);
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        if (router.query.email) {
          getOrders(router.query.email)
          getCategories()
          console.log(movie);
        }
      }, [router.query.movie]);

      async function getOrders(email) {
        try {
          const {data} = await axios.get("http://localhost:8080/orders/getUserOrders" + email,
          {
              headers: {
                  "Access-Control-Allow-Origin": "http://localhost:8080",
                  "MediaType": "application/json",
                  "Authorization": "Bearer " + localStorage.getItem('jwt')
              }
      
          })
    
          let res = {data}
          console.log(res)
          setOrders(res)
          setIsLoadingCategories(false)
    
        } catch (err) {
          console.error("Error fetching movies:", err);
        }
      }

      async function getCategories() {
        try {
          const {data} = await axios.get("http://localhost:8080/categories/all",
          {
              headers: {
                  "Access-Control-Allow-Origin": "http://localhost:8080",
                  "MediaType": "application/json",
                  "Authorization": "Bearer " + localStorage.getItem('jwt')
              }
      
          })
    
          let res = {data}
          console.log(res)
          setCategories(res)
          setIsLoadingCategories(false)
    
        } catch (err) {
          console.error("Error fetching movies:", err);
        }
      }

    return(
        <div>
            <h1>Hello</h1>
        </div>
    )
}