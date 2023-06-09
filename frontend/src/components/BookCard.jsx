import React from 'react';
import { Card, CardContent, Typography, CardMedia, Button, Box } from '@mui/material';

const containerStyles = {
  maxWidth: '250px',
  height: '500px',
  margin: '10px',
  padding: '5px',
  backgroundColor: '#F2F2F2',
};

const imageStyles = {
  height: '250px',
  objectFit: 'contain',
};

const truncateStyles = {
  overflow: 'hidden',
  textOverflow: 'ellipsis',
  display: '-webkit-box',
  WebkitLineClamp: 3,
  WebkitBoxOrient: 'vertical',
};

function BookCard({ book }) {
  const { description, name, price, imgUrl } = book;

  return (
    <Card style={containerStyles}>
        <CardContent>
            <Typography variant="h5" component="div">
                {name}
            </Typography>
            <CardMedia component="img" style={imageStyles} image={imgUrl} alt={name} />
            <Box sx={truncateStyles}>
                <Typography variant="body2" color="text.secondary">
                    {description}
                </Typography>
            </Box>
            <Typography variant="h6" component="div">
                Price: ${price}
            </Typography>
        </CardContent>
        <Button variant="contained" color="primary">
        Add to Cart
        </Button>
    </Card>
  );
}

export default BookCard;