import React from "react";
import { Typography } from "@mui/material";

interface IProps {
  el: any;
}

const ItemRow: React.FC<IProps> = ({ el }) => {
  let { name, price } = el;
  return (
    <div
      style={{
        border: ".5px solid gray",
        margin: "10px",
        borderRadius: "5px",
        padding: "10px",
      }}
    >
      <Typography>
        <b>Nombre del CD: </b>
        {name}
      </Typography>
      <Typography>
        <b>Precio del CD: </b>
        {price}
      </Typography>
    </div>
  );
};

export default ItemRow;
