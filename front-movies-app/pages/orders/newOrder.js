import { Container, TextField, Button } from '@mui/material';
import { useEffect, useState } from 'react';
import axios from 'axios'
import { useRouter } from 'next/router';
export default function newOrder() {
    const router = useRouter()
    const baseURL = "http://localhost:8080";
    
    const [order, setOrder] = useState({
        "status": 'en proceso',
        "total": 0,
        "user": JSON.parse(localStorage.getItem("user")),
        "orderNumber": '',
        "movies": []
    })
   
    const handleInputChange = async (event) => {
        const { name, value } = event.target;
        setOrder((prevOrderData) => ({
          ...prevOrderData,
          [name]: value,
        }));
      };

    const handleCreateOrder = async () => {
        console.log(order)
        console.log(localStorage.getItem('jwt'))
        const {data} = await axios.post(
            baseURL+"/orders/", 
            order,
            {
                headers: {
                    "Access-Control-Allow-Origin": baseURL,
                    "Content-Type": "application/json",
                    "Authorization" : "Bearer " + localStorage.getItem("jwt")
                }
            }
          );

          console.log(data)
          let email = JSON.parse(localStorage.getItem("user")).email
          router.push(`/orders/${email}`)

    }

    return (
        <Container maxWidth="sm" style={{ margin: 'auto', marginTop: '25vh' }}>
      <div style={{ border: '1px solid #9E9E9E', borderRadius: '25px', textAlign: 'center', padding: '2em' }}>
        <h2>Crear nueva orden</h2>
        <form>
          <TextField
            label="Numero de Orden"
            type="text"
            fullWidth
            value={order.orderNumber || ''}
            name="orderNumber"
            margin="normal"
            onChange={handleInputChange}
          />
          <Button variant="contained" color="primary" fullWidth onClick={handleCreateOrder}>
            Crear orden
          </Button>
        </form>
      </div>
    </Container>
    )
}