import { Grid, Card, CardContent, Typography, CardMedia } from '@mui/material';
import '../styles/categories.css';
import { NavigateFunction, useNavigate } from 'react-router-dom';

const Categories = () => {
    const navigation: NavigateFunction = useNavigate();

    const handleClick = () => {
        navigation("/orders");
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
                            height= "auto"
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
            </Grid>
        </div>    
    );
};

export default Categories