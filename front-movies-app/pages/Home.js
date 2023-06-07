import Head from 'next/head'
import styles from '../styles/Home.module.css'
import Navigation from '../components/Navigation'
import Carousel from '../components/Carousel'
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '../components/Card';


function Home () {

  

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