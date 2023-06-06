import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { useState, useEffect } from "react";

interface IProps {
  el: any;
  setOrderActive: any;
  handleAddItemsOrder: any;
  orderActive: any;
}

const CardItem: React.FC<IProps> = ({
  el,
  setOrderActive,
  handleAddItemsOrder,
  orderActive,
}) => {
  let { description, name, price, category } = el;
  const [buttonDisabled, setButtonDisabled] = useState(false);

  const handleClickButton = () => {
    handleAddItemsOrder(el);
    setOrderActive(true);
    setButtonDisabled(true);
  };

  useEffect(() => {
    if (!orderActive) {
      setButtonDisabled(false);
    }
  }, [orderActive]);

  return (
    <Card sx={{ maxWidth: 345, minWidth: "26%" }}>
      <CardMedia
        sx={{ height: 140 }}
        image={`https://picsum.photos/300/200?random=${Math.random()}`}
        title={name}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {description}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {price}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {category}
        </Typography>
      </CardContent>
      <CardActions>
        <Button
          onClick={() => handleClickButton()}
          disabled={buttonDisabled}
          size="small"
        >
          Agregar a la orden
        </Button>
      </CardActions>
    </Card>
  );
};

export default CardItem;
