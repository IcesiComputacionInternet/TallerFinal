import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'
import Navigation from '../components/Navigation'
import Carousel from '../components/Carousel'
import { Container, TextField, Button } from '@mui/material';
import { useState } from 'react';
import { useRouter } from 'next/router';
import axios from "axios";

export default function Login() {

  const baseURL = "http://localhost:8080";

  const router = useRouter();

  const [loginData, setLoginData] = useState({
    username: '',
    password: ''
  })

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setLoginData((prevLoginData) => ({
      ...prevLoginData,
      [name]: value
    }));
  };


  const handleLogin = async () => {
    console.log(loginData)
    const username = loginData.username
    const password = loginData.password
    const {data} = await axios.post(
      baseURL+"/token", 
      {
        username, password
      },
      {
          headers: {
              "Access-Control-Allow-Origin": baseURL,
          }
      }
    );
      if(data.token){
          localStorage.setItem("jwt", data.token);
          
          //setLogin(true);
          router.push("/Home");
      }else{
          console.log(data);
          alert("invalid credentials");
      }
  }

  return (
    <Container maxWidth="sm" style={{ margin: 'auto', marginTop: '25vh' }}>
      <div style={{ border: '1px solid #9E9E9E', borderRadius: '25px', textAlign: 'center', padding: '2em' }}>
        <h2>Iniciar sesión</h2>
        <form>
          <TextField
            label="Username"
            type="text"
            fullWidth
            margin="normal"
            name="username"
            value={loginData.username || ''}
            onChange={handleInputChange}
          />
          <TextField
            label="Contraseña"
            type="password"
            fullWidth
            value={loginData.password || ''}
            name="password"
            margin="normal"
            onChange={handleInputChange}
          />
          <Button variant="contained" color="primary" fullWidth onClick={handleLogin}>
            Log in
          </Button>
          <Button variant="contained" color="primary" fullWidth onClick={() => router.push("/signin")}>
            Sing up
          </Button>
        </form>
      </div>
    </Container>
  )
}
