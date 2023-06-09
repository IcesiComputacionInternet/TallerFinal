import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navigation from '../../components/Navigation'


export default function orders() {

    const router = useRouter();

    const urlOrder = "http://localhost:3000/orders/order/";

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
            {isLoadingCategories || isLoadingCurrent ? (
                <h1>Loading...</h1>
            ) : (
                <Navigation user={current.data} categories={categories}/>
            )}

            <div className="text-center">
                {isLoadingCurrent ? (
                    <h1>Loading...</h1>
                ) : (
                    <h1>{current.data.firstName}'s Orders</h1>
                )}

                {isLoading ? (
                    <h1>Loading...</h1>
                ):(
                    <div>
                        <ul className="list-group">
                            {
                            orders.data.map((order) => (
                                <a href = {urlOrder + order.orderNumber} class="list-group-item list-group-item-action">
                                <div className="d-flex w-100 justify-content-between">
                                  <h5 className="mb-1">{order.orderNumber}</h5>
                                  <small className="text-muted">{order.status}</small>
                                </div>
                                <p className="mb-1">Total cost: {order.total}</p>
                                <small>Click to see the content.</small>
                              </a>
                            ))}
                        </ul>
                    </div>
                )}
            </div>
        </div>
    )
}