import { useState, useEffect } from "react";
import axios from "axios";
import { Grid, Card, CardContent, Typography, CardMedia, Button } from '@mui/material';
import shop from '../styles/shop.css';

const baseUrl = "http://localhost:8080/api/items";

interface Item {
    id: number;
    name: string;
    description: string;
    price: number;
    imageUrl: string;
}

const products = [
    { id: 1, name: 'Producto 1', price: 10, image: 'path_to_image_1' },
    { id: 2, name: 'Producto 2', price: 20, image: 'path_to_image_2' },
    { id: 3, name: 'Producto 3', price: 30, image: 'path_to_image_3' },
    // Agrega más productos aquí...
  ];

export default function Shop() {
    const [items, setItems] = useState<Item[]>([]); // [state, function]


    return (
        <div className="container">
            <Typography variant="h4" component="h2" style={{ marginBottom: '20px' }}>
                Tienda
            </Typography>
            <Grid container spacing={2}>
                {products.map((product) => (
                <Grid item xs={12} sm={6} md={4} key={product.id}>
                    <Card className="productCard">
                    <CardMedia
                        component="img"
                        height="140"
                        image={product.image}
                        alt={product.name}
                    />
                    <CardContent>
                        <Typography variant="h6" component="div" style={{ textAlign: 'left' }}>
                        {product.name}
                        </Typography>
                        <Typography variant="h6" component="div" style={{ textAlign: 'right' }}>
                        ${product.price}
                        </Typography>
                    </CardContent>
                        <div className="cardActions">
                            <Button variant="contained" color="primary">
                            Add to Cart
                            </Button>
                        </div>
                    </Card>
                </Grid>
                ))}
            </Grid>
        </div>
    );
};


