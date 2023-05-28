import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'
import myImage from '../resources/logo.png'
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Divider  from '@mui/material/Divider'


const Home: NextPage = () => {
  
  return (
    <div className={styles.flexContainer}>
      <div className={styles.section1}>
        <div>
          <h1>Bienvenido</h1>
        </div>
        <div className={styles.form}>
            <h3>Nombre de usuario</h3>
            <TextField id="outlined-basic" 
            label="Usuario" 
            variant="outlined" 
            sx={{maxWidth: 250}} 
            size='small' 
            margin='normal'/>

            <TextField 
            id="outlined-basic" 
            label="Contraseña" 
            variant="outlined" 
            sx={{maxWidth: 250}} 
            size='small' 
            margin='normal'
            type='password'/>
        </div>
        <Divider sx={{marginTop: 2, marginBottom: 2}}/>
        <div className={styles.buttons}>
          <Button variant="contained" sx={{maxWidth: 170}} >Iniciar Sesión</Button>
          <Button variant="outlined" sx={{maxWidth: 170, marginLeft:5}}>Registrarse</Button>
        </div>
      </div>
      <div className={styles.section2}>
        <div className={styles.logo}>
          <img src={myImage.src} alt="Logo" width={150} height={150}/>
        </div>
        <div>
          <h1> E-Shop de televisores.com</h1>
        </div>
      </div>
    </div>
  )
}

export default Home
