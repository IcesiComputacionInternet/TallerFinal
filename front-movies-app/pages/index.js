import Head from 'next/head'
import styles from '../styles/Home.module.css'
import Navigation from '../components/Navigation'
import Carousel from '../components/Carousel'
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';
import axios from 'axios';

export default function Home() {

  const url = "http://localhost:8080/movies/"

  console.log(getToken())

  async function getToken() {
    
    return axios.post(url + "all", {
      headers: {
        "username": "noname@email.com",
        "password": "password"
      }
    })

  }

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
