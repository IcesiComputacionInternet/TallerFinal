import { useState, useEffect } from "react";
import axios from "axios";
import { Grid, Card, CardContent, Typography, CardMedia } from '@mui/material';
import AddBoxIcon from '@mui/icons-material/AddBox';
import '../styles/categories.css';
import { NavigateFunction, useNavigate } from 'react-router-dom';

const baseURL = "http://localhost:8080/api/categories";

interface Category {
    name: string;
    description: string;
    imageURL: string;
}

const Categories = () => {
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
            const response = await axios.get(baseURL + "/get/all", 
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            });
            console.log("Categories fetched: ", response.data);
            const mappedCategories = response.data.map((
                { name, description, imageURL}: { name: string, description: number, imageURL: string,}) => 
                ({
                    name,
                    description,
                    imageURL,
                }
                ));
            setCategories(mappedCategories);
        } catch (error) {
            console.error("Error fetching the categories: ", error);
        }
    };

    return (
        <div className="containerGrid">    
            <Typography variant="h4" component="div" className="title" >
                CATEGORIES
            </Typography>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6} md={6}>
                    <Card className="cardCategory" onClick={handleClick}>
                        <CardMedia
                            component="img"
                            height= "auto"
                            image="src/resources/images/portatiles.webp"
                            alt="portatiles"
                        />
                        <CardContent className="cardContent">
                            <Typography variant="h5" component="div">
                                PORTATILES
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={12} sm={6} md={6}>
                    <Card className="cardCategory" onClick={handleClick}>
                        <CardMedia
                            component="img"
                            height= "auto"
                            image="src/resources/images/desktop.webp"
                            alt="portatiles"
                        />
                        <CardContent>
                            <Typography variant="h5" component="div">
                                COMPUTADORES DE ESCRITORIO
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={12} sm={6} md={6}>
                    <Card className="cardCategory" onClick={handleClick}>
                        <CardMedia
                            component="img"
                            height= "auto"
                            image="src/resources/images/all-in-one.png"
                            alt="portatiles"
                        />
                        <CardContent>
                            <Typography variant="h5" component="div">
                                ALL-IN-ONE
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={12} sm={6} md={6}>
                    <Card className="cardCategory" onClick={handleClick}>
                        <CardMedia
                            component="img"
                            height="auto"
                            image="src/resources/images/gaming.webp"
                            alt="portatiles"
                        />
                        <CardContent>
                            <Typography variant="h5" component="div">
                                GAMING
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={12} sm={12} md={12} style={{ display: 'flex', justifyContent: 'center' }}>
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
};

export default Categories