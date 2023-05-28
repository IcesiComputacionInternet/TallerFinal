import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'
import myImage from '../resources/logo.png'

const Home: NextPage = () => {
  
  return (
    <div className={styles.flexContainer}>
      <div className={styles.section1}>
        <div>
          <h1>Bienvenido</h1>
        </div>
        <div className={styles.form}>
          
            <input type="text" placeholder="Usuario" style={{margin:5 }}/>
            <input type="password" placeholder="Contraseña" style={{margin:5 }}/>
            <button style={{maxWidth:150}}>Ingresar</button>
          
        </div>
      </div>
      <div className={styles.section2}>
        <div className={styles.logo}>
          <img src={myImage.src} alt="Logo" width={150} height={150}/>
        </div>
      </div>
    </div>
  )
}

export default Home
