import axios from "axios";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import Navigation from "../components/Navigation";
import MoviesList from "../components/MoviesList";
import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content';

export default function Movies() {
  const baseUrl = 'http://localhost:8080'
  const router = useRouter();
  const [movies, setMovies] = useState([]);
  const [current, setCurrent] = useState({});
  const [isLoadingMovies, setIsLoadingMovies] = useState(true);
  const [categories, setCategories] = useState([]);
  const [isLoadingCategories, setIsLoadingCategories] = useState(true);
  const [isReady, setIsReady] = useState(false)

  const getCurrentUser = async () => {
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
      console.log("Sape");
      console.log(res.data);
      setCurrent(res);
      // setCurrent(res)
      // setIsLoadingCurrent(false)
      setIsReady(true)
    } catch (err) {
      console.error("Error fetching current user:", err);
    }
  };

  async function getMovies() {
    try {
      const { data } = await axios.get("http://localhost:8080/movies/all", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          MediaType: "application/json",
          Authorization: "Bearer " + localStorage.getItem("jwt"),
        },
      });

      let res = { data };
      console.log(res);
      setMovies(res);
      setIsLoadingMovies(false);
    } catch (err) {
      console.error("Error fetching movies:", err);
    }
  }

  async function getCategories() {
    try {
      const { data } = await axios.get("http://localhost:8080/categories/all", {
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
      console.error("Error fetching categories:", err);
    }
  }
  
  const handleNotAuthorized = (current) => {
      if (current.data.role.name != "ADMIN") {
          router.push("/acceso-denegado");
        }
  }
  useEffect(() => {
    if (isReady) {
        handleNotAuthorized(current)
    }
  }, [current, isReady])

  useEffect(() => {
    const token = localStorage.getItem("jwt");
    if (!token) {
      router.push("/login");
    } else {
      getCurrentUser();
      getCategories();
      getMovies()
      console.log("Yo! " + current)
    }

    getMovies();
  }, []);

  const MySwal = withReactContent(Swal);

  const addMovie = async () => {
    Swal.fire({
        title: 'En progreso',
        text: 'Todavía no añadimos peliculas',
        icon: 'info',
        confirmButtonText: 'Ok'
    })
    try {
      const { value: formValues } = await MySwal.fire({
        title: 'Add Movie',
        html: `
          <input id="name" class="swal2-input" placeholder="Name">
          <input id="description" class="swal2-input" placeholder="Description">
          <input id="price" class="swal2-input" placeholder="Price">
          <input id="imageURL" class="swal2-input" placeholder="Image URL">
          <input id="pgRating" class="swal2-input" placeholder="PG Rating">
          <input id="categoryName" class="swal2-input" placeholder="Category Name">
          <input id="categoryDescription" class="swal2-input" placeholder="Category Description">
        `,
        focusConfirm: false,
        preConfirm: () => {
          return {
            name: document.getElementById('name').value,
            description: document.getElementById('description').value,
            price: document.getElementById('price').value,
            imageURL: document.getElementById('imageURL').value,
            pgRating: document.getElementById('pgRating').value,
            categoryDTO: {
              name: document.getElementById('categoryName').value,
              description: document.getElementById('categoryDescription').value,
            },
          };
        },
      });
  
      if (formValues) {
        // Aquí puedes enviar los datos del formulario a través de una solicitud POST
        try {

          const {data} = axios.post(`${baseUrl}/movies/`,formValues, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              "MediaType": "application/json",
              "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
          })
  
          let res = {data}
          console.log(res)
          router.push("/Home")
        } catch(err) {
          Swal.fire({
            title: 'Hubo un error!',
            text: 'No se puedo crear la película',
            icon: 'error',
            confirmButtonText: 'Ok'
          })
        }
        console.log(formValues);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="w-100 vw-100 text-center">
      {isLoadingCategories ? (
        <h1>Loading...</h1>
      ) : (
        <Navigation user={current.data} categories={categories} />
      )}
      <h2>Movie database / Add - Delete movies</h2>
        <button className="btn btn-primary mb-2" onClick={addMovie}>
          Añadir película
        </button>
      <div className="text-center d-flex align-items-center justify-content-center">
        {isLoadingMovies ? (
          <h1>Loading...</h1>
        ) : (
          <MoviesList
            movies={movies.data}
            buttonAction={"Movies"}
            orderNumber={""}
          />
        )}
      </div>
    </div>
  );
}
