import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navigation from '../../components/Navigation'

const movie = () => {
  const router = useRouter();

  const [categories, setCategories] = useState([]);
  console.log("rou");
  console.log(router.query.movie);

  useEffect(() => {
    if (router.query.movie) {
      let encodedMovie = encodeURIComponent(router.query.movie);
      getData(encodedMovie);
      getCategories()
      console.log(movie);
    }
  }, [router.query.movie]);

  const [isLoading, setIsLoading] = useState(true);

  const [movie, setMovie] = useState({
    name: "",
    description: "",
    price: 0,
    imageURL: "",
    categoryName: "",
    pgRating: "",
  });

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
      setIsLoading(false)

    } catch (err) {
      console.error("Error fetching movies:", err);
    }
  }


  return (
    <div>
        <Navigation categories={categories}/>
      {isLoading ? (
        <div class="text-center">
            <div class="spinner-grow spinner-grow-sm" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            <div class="spinner-grow spinner-grow-sm" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            <div class="spinner-grow spinner-grow-sm" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>
      ) : (
        <div style={{display: "flex", flexDirection: 'column', justifyContent: 'center', alignItems: 'center'}}>
          <h1>{movie.name}</h1>
          <div>
            <img  width="300" height="400" src={movie.imageURL} alt="..." />
          </div>
          <div width= "100" style={{display: "flex", flexDirection: 'column', justifyContent: 'center', alignItems: 'center'}}>
            <h2>{movie.name}</h2>
            <h4>Category: {movie.categoryName}</h4>
            <h4>Ranking: {movie.pgRating}</h4>
            <p>{movie.description}</p>
          </div>

          <div style={{display: "flex"}}>
          <button class= "btn btn-primary">
            Buy For {movie.price}$
          </button>
          <button class= "btn btn-primary">
            Add To Basket
          </button>
          </div>
          
        </div>
      )}
    </div>
  );
};

export default movie;
