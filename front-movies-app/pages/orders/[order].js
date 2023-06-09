import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navigation from '../../components/Navigation'

export default function orderDetails() {

    const router = useRouter();

    const [categories, setCategories] = useState([]);
    const [orders, setOrders] = useState([]);

    const [isLoading, setIsLoading] = useState(true);
    const [isLoadingCategories, setIsLoadingCategories] = useState(true);

    const [current, setCurrent] = useState([]);
    const [isLoadingCurrent, setIsLoadingCurrent] = useState(true);

    useEffect(() => {
        if (router.query.email) {
          getOrders(router.query.email)
          getCategories()
          getCurrentUser()
        }
    }, [router.query.email]);

    async function getCurrentUser() {
        try {
          const {data} = await axios.get("http://localhost:8080/users/CurrentUser",
          {
              headers: {
                  "Access-Control-Allow-Origin": "http://localhost:8080",
                  "MediaType": "application/json",
                  "Authorization": "Bearer " + localStorage.getItem('jwt')
              }
      
          })
    
          let res = {data}
          console.log(res)
          setCurrent(res)
          setIsLoadingCurrent(false)
    
        } catch (err) {
          console.error("Error fetching current user:", err);
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

      
      async function getOrders(email) {
        try {
          const {data} = await axios.get("http://localhost:8080/orders/getUserOrders/" + email,
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
          setIsLoading(false)
    
        } catch (err) {
          console.error("Error fetching movies:", err);
        }
      }

      

    
}