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
  
  const [isLoadingMovies, setIsLoadingMovies] = useState(true);

  useEffect(() => {
    getMovies()
    console.log(movies)
   
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


  

  return (
        <div className={styles.container}>
      <Head>
        <title>Home Page</title>
        <link rel="icon" href="/movie.png" />
        
      </Head>
      
      <Navigation/>
      <h2>New movies</h2>

      {isLoadingMovies ? (
        <h1>Loading...</h1>
      ) : (
        <div className='text-center d-flex align-items-center justify-content-center'>
          <MoviesList movies={movies.data} buttonAction={'Home'} orderNumber={''}/>
        </div>
      )}
      
      
      
      
      
    </div>
    )
}

/*

*/

export default Home;