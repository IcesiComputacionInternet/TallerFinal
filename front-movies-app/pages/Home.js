import Head from 'next/head'
import styles from '../styles/Home.module.css'
import Navigation from '../components/Navigation'
import Carousel from '../components/Carousel'
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';
import { useState } from 'react';
import axios from "axios";

function Home () {

  const movies = getMovies();

  async function getMovies() {
    console.log("Hola")
    return axios.get("http://localhost:8080/movies/all",
      {
          headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              "MediaType": "application/json",
              "Authorization": "Bearer " + localStorage.getItem('jwt')
          }

      }
    )
  }

  console.log(movies)

  return (
        <div className={styles.container}>
      <Head>
        <title>Home Page</title>
        <link rel="icon" href="/movie.png" />
        
      </Head>
      
      <Navigation/>
      <Carousel/>
      <Card/>
      
      
    </div>
    )
}

export default Home;