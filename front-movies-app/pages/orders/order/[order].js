import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navigation from '../../../components/Navigation'
import MoviesList from "../../../components/MoviesList";

export default function orderDetails() {

    const router = useRouter();

    const [order, setOrder] = useState({
        orderNumber: "",
        movies: [],
        total: 0,
        status: "",
    });

    const [isLoading, setIsLoading] = useState(true);

    const [current, setCurrent] = useState([]);
    const [isLoadingCurrent, setIsLoadingCurrent] = useState(true);

    useEffect(() => {
        if (router.query.order) {
          getOrders(router.query.order)
          getCurrentUser()
        }
    }, [router.query.order]);

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


      async function getOrders(order) {
        try {
          const {data} = await axios.get("http://localhost:8080/orders/findByNumber/" + order,
          {
              headers: {
                  "Access-Control-Allow-Origin": "http://localhost:8080",
                  "MediaType": "application/json",
                  "Authorization": "Bearer " + localStorage.getItem('jwt')
              }
      
          })
          setOrder({
            orderNumber: data.orderNumber,
            movies: data.movies,
            total: data.total,
            status: data.status,
          });
          setIsLoading(false)
    
        } catch (err) {
          console.error("Error fetching movies:", err);
        }
      }

      return(
        <div className="text-center">
            <Navigation/>
            <div>
              {isLoadingCurrent || isLoading ? (
                      <h1>Loading...</h1>
                  ) : (
                      <h1>{current.data.firstName}'s Order {order.orderNumber}</h1>
                  )}

              {isLoading ? (
                  <h1>Loading...</h1>
              ) : (
                <MoviesList movies={order.movies} buttonAction={'Order'} orderNumber={order.orderNumber}/>
              )}
            </div>
        </div>
      )

    
}