
import { Container, TextField, Button } from '@mui/material';
import { useState } from 'react';
import { useRouter } from 'next/router';
import axios from "axios";

export default function Login() {

  const baseURL = "http://localhost:8080";

  const router = useRouter();

  const [loginData, setLoginData] = useState({
    email: '',
    password: '',
    firstName: '',
    lastName:'',
    phone:'',
    address:'',
    birthDate:'',
    roleName:'USER'

  })

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setLoginData((prevLoginData) => ({
      ...prevLoginData,
      [name]: value
    }));
  };

  const  handleSingup = async () => {
    console.log(loginData)
    const {data} = await axios.post(
        baseURL+"/users/", 
        {
          loginData
        },
        {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            "MediaType": "application/json",
            "Authorization": "Bearer " + localStorage.getItem('jwt')
        }
    })
    routr.push("/login")
  }

  return (
    <Container maxWidth="sm" style={{ margin: 'auto', marginTop: '25vh' }}>
      <div style={{ border: '1px solid #9E9E9E', borderRadius: '25px', textAlign: 'center', padding: '2em' }}>
        <h2>Crear Usuario</h2>
        <form>
          <TextField
            label="Name"
            type="text"
            fullWidth
            value={loginData.firstName || ''}
            name="firstName"
            margin="normal"
            onChange={handleInputChange}
          />
          <TextField
            label="Last Name"
            type="text"
            fullWidth
            value={loginData.lastName || ''}
            name="lastName"
            margin="normal"
            onChange={handleInputChange}
          />
          <TextField
            label="Email"
            type="text"
            fullWidth
            value={loginData.email || ''}
            name="email"
            margin="normal"
            onChange={handleInputChange}
          />
          <TextField
            label="Password"
            type="password"
            fullWidth
            value={loginData.password || ''}
            name="password"
            margin="normal"
            onChange={handleInputChange}
          />
          <TextField
            label="Phone"
            type="text"
            fullWidth
            value={loginData.phone || ''}
            name="phone"
            margin="normal"
            onChange={handleInputChange}
          />
          <TextField
            label="Address"
            type="text"
            fullWidth
            value={loginData.address || ''}
            name="address"
            margin="normal"
            onChange={handleInputChange}
          />
          <TextField
            label="Birthday"
            type="text"
            fullWidth
            value={loginData.birthDate || ''}
            name="birthDate"
            margin="normal"
            onChange={handleInputChange}
          />
          
          <Button variant="contained" color="primary" fullWidth onClick={handleSingup}>
            Sing up
          </Button>
        </form>
      </div>
    </Container>
  )
}
