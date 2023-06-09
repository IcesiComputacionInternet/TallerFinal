import { useState, useEffect } from "react";
import axios from "axios";
import { Grid, Card, CardContent, Typography, CardMedia } from '@mui/material';
import AddBoxIcon from '@mui/icons-material/AddBox';
import '../styles/categories.css';
import { NavigateFunction, useNavigate } from 'react-router-dom';

const baseUrl = "http://localhost:8080/api/categories";

interface Category {
    id: number;
    name: string;
    description: string;
    imageUrl: string;
}

export default function Categories() {
    const [categories, setCategories] = useState<Category[]>([]); // [state, function
    const navigation: NavigateFunction = useNavigate();
    const token = localStorage.getItem("jwt");

    const handleClick = () => {
        navigation("/orders");
    };

    
    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = async () => {
        try {
            const response = await axios.get(baseUrl + "/get/all", 
            {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": "Bearer " + token,
                    "Access-Control-Allow-Origin": baseUrl,
                }
            });
            console.log("Categories fetched: ", response.data);
            const mappedCategories = response.data.map((
                { name, description, imageUrl}: { name: string, description: number, imageUrl: string,}) => 
                ({
                    name,
                    description,
                    imageUrl,
                }
                ));
            setCategories(mappedCategories);
        } catch (error) {
            console.error("Error fetching the categories: ", error);
        }
    };
    
    const gridColumns = Math.ceil(categories.length / 2); // Divide the number of categories into 2 columns. If the number is odd, add an extra column with ceiling function.


    return (
        <div className="containerGrid">    
            <Typography variant="h4" component="div" className="title" >
                CATEGORIES
            </Typography>
            <Grid container spacing={2}>
                {categories.map((category) => (
                    <Grid item xs={12} sm={6} md={12 / gridColumns} key={category.id}>
                        <Card className="cardCategory" onClick={handleClick}>
                            <CardMedia
                                component="img"
                                height= "auto"
                                image= {category.imageUrl}
                                alt= {category.name}
                            />
                            <CardContent className="cardContent">
                                <Typography variant="h5" component="div">
                                    {category.name}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
                <Grid item xs={12} sm={12} md={12}>
                    <Card className="cardCategory" id="addCategory">
                        <CardContent>
                            <AddBoxIcon sx={{ fontSize: 120 }} className="addIcon"/>
                            <Typography variant="h5" component="div">
                                AGREGAR CATEGORÍA
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
        </div>    
    );
}