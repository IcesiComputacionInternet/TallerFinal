import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import Navigation from "../../components/Navigation";
import Swal from "sweetalert2";

const movie = () => {
  const baseUrl = "http://localhost:8080";
  const router = useRouter();

  const [categories, setCategories] = useState([]);
  console.log("rou");
  console.log(router.query.movie);

  const [current, setCurrent] = useState([]);
  const [orders, setOrders] = useState([]);
  const [isLoadingCurrent, setIsLoadingCurrent] = useState(true);

  useEffect(() => {
    if (router.query.movie) {
      let encodedMovie = encodeURIComponent(router.query.movie);
      getData(encodedMovie);
      getCategories();
      console.log(movie);
      getCurrentUser();
    }
  }, [router.query.movie]);

  const [isLoading, setIsLoading] = useState(true);
  const [isLoadingCategories, setIsLoadingCategories] = useState(true);
  const [isReady, setIsReady] = useState(false);

  const [movie, setMovie] = useState({
    name: "",
    description: "",
    price: 0,
    imageURL: "",
    categoryName: "",
    pgRating: "",
  });

  async function getCurrentUser() {
    try {
      const { data } = await axios.get(
        "http://localhost:8080/users/CurrentUser",
        {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            MediaType: "application/json",
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        }
      );

      let res = { data };
      console.log(res);
      setCurrent(res);
      setIsLoadingCurrent(false);
    } catch (err) {
      console.error("Error fetching current user:", err);
    }
  }

  async function getData(name) {
    try {
      const { data } = await axios.get("http://localhost:8080/movies/" + name, {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          MediaType: "application/json",
          Authorization: "Bearer " + localStorage.getItem("jwt"),
        },
      });
      console.log({ data });
      console.log(data);
      setMovie({
        name: data.name,
        description: data.description,
        imageURL: data.imageURL,
        pgRating: data.pgRating,
        price: data.price,
        categoryName: data.categoryName,
      });
      //console.log(movie);
      setIsLoading(false);
    } catch (err) {
      console.error("Error fetching movies:", err);
    }
  }

  async function getCategories() {
    try {
      const { data } = await axios.post("http://localhost:8080/categories/all", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          MediaType: "application/json",
          Authorization: "Bearer " + localStorage.getItem("jwt"),
        },
      });

      let res = { data };
      console.log(res);
      setCategories(res);
      setIsLoadingCategories(false);
    } catch (err) {
      console.error("Error fetching movies:", err);
    }
  }

  useEffect(() => {
    if (isReady) {
      handleSwal(orders);
    }
  }, [isReady, orders]);

  async function handleOrder() {
    console.log("Order!");
    try {
      let email = await JSON.parse(localStorage.getItem("user")).email;
      console.log(email);
      const response = await axios.get(
        "http://localhost:8080/orders/getUserOrders/" + email,
        {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            MediaType: "application/json",
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        }
      );

      let { data } = response;
      console.log(data);
      let newData = data;
      console.log(newData);
      setOrders(data);
      console.log(orders);
      setIsReady(true);
    } catch (err) {
      console.error("Error fetching movies:", err);
      Swal.fire({
        title: "Error!",
        text: "Ha habido un problema obteniendo las ordenes.",
        icon: "error",
        confirmButtonText: "Ok",
      });
    }
  }

  async function handleSwal(orders) {
    const orderOptions = orders
      .filter((order) => order.status != "confirmada")
      .map((order) => order.orderNumber);
    console.log(orders);
    const { value: selectedOrderNumber } = await Swal.mixin({
      input: "select",
      inputOptions: {
        ...orderOptions.reduce((obj, orderNumber) => {
          obj[orderNumber] = orderNumber;
          return obj;
        }, {}),
      },
      inputPlaceholder: "Select an order",
      showCancelButton: true,
      confirmButtonText: "Confirm",
      cancelButtonText: "Cancel",
      inputValidator: (value) => {
        return new Promise((resolve) => {
          if (value !== "") {
            resolve();
          } else {
            resolve("Please select an order");
          }
        });
      },
    }).fire();

    if (selectedOrderNumber) {
      console.log("Selected order: " + selectedOrderNumber);
      handleAddMovie(selectedOrderNumber);
    }
  }

  async function handleAddMovie(orderNumber) {
    console.log(orderNumber)
    console.log(movie.name)
    let order = {
      orderNumber: orderNumber,
      movieName: movie.name
    }
    console.log("Order: ",order)
    try {
      const {data} = await axios.post(
        `${baseUrl}/orders/addMovie`,
        order,
        {
          headers: {
            "Access-Control-Allow-Origin": baseUrl,
            "Content-Type": "application/json",
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        }
      );

      let res = {data}
      console.log("Res: ", res)
      if (res) {
        Swal.fire({
          title: "Todo correcto!",
          text: "Se ha añadido la pelicula a la orden",
          icon: "success",
          confirmButtonText: "Ok",
        });
      }
    } catch (err) {
      console.error(err)
      Swal.fire({
        title: "Ha habido un error",
        text: "No se ha podido añadir la pelicula a la orden",
        icon: "error",
        confirmButtonText: "Ok",
      });
    }
  }
  return (
    <div>
      {isLoadingCategories || isLoadingCurrent ? (
        <h1>Loading...</h1>
      ) : (
        <Navigation user={current.data} categories={categories} />
      )}

      {isLoading ? (
        <div className="text-center">
          <div className="spinner-grow spinner-grow-sm" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
          <div className="spinner-grow spinner-grow-sm" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
          <div className="spinner-grow spinner-grow-sm" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      ) : (
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <h1>{movie.name}</h1>
          <div>
            <img width="300" height="400" src={movie.imageURL} alt="..." />
          </div>
          <div
            width="100"
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <h2>{movie.name}</h2>
            <h4>Category: {movie.categoryName}</h4>
            <h4>Ranking: {movie.pgRating}</h4>
            <p>{movie.description}</p>
          </div>

          <div style={{ display: "flex" }}>
            <button className="btn btn-primary">Buy For {movie.price}$</button>
            <button className="btn btn-primary" onClick={handleOrder}>
              Add To Order
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default movie;
