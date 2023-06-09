import Head from 'next/head'
import styles from '../styles/Home.module.css'
import Navigation from '../components/Navigation'
import Carousel from '../components/Carousel'
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';
import { useState, useEffect } from 'react';
import axios from "axios";
import MoviesList from '../components/MoviesList';

function Home () {


  const [movies, setMovies] = useState([]);
  const [categories, setCategories] = useState([]);
  const [isLoadingCategories, setIsLoadingCategories] = useState(true);
  const [isLoadingMovies, setIsLoadingMovies] = useState(true);

  useEffect(() => {
    getMovies()
    console.log(movies)
    getCategories()
    console.log(categories)
  }, [])

  async function getMovies() {
    try {
      const {data} = await axios.get("http://localhost:8080/movies/all",
      {
          headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              "MediaType": "application/json",
              "Authorization": "Bearer " + localStorage.getItem('jwt')
          }
  
      })

      let res = {data}
      console.log(res)
      setMovies(res)
      setIsLoadingMovies(false)

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
      console.error("Error fetching categories:", err);
    }
  }

  

  return (
        <div className={styles.container}>
      <Head>
        <title>Home Page</title>
        <link rel="icon" href="/movie.png" />
        
      </Head>
      
      {isLoadingCategories ? (
      <h1>Loading...</h1>
      ) : (
        <Navigation categories={categories}/>
      )}
      
      <Carousel/>

      {isLoadingMovies ? (
        <h1>Loading...</h1>
      ) : (
        <MoviesList movies={movies} />
      )}
      
      
      
      
      
    </div>
    )
}

/*

*/

export default Home;