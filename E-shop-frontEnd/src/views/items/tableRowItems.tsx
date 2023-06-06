import React from "react";
import { TableRow, TableCell, Button } from "@mui/material";

interface IProps {
  el: any;
  setDataToEdit: any;
  deleteData: any;
}

const TableRowItems: React.FC<IProps> = ({ el, setDataToEdit, deleteData }) => {
  let { description, name, price, imageUrl, categoryId } = el;
  return (
    <TableRow sx={{ textAlgin: "center" }}>
      <TableCell style={{ textAlign: "center" }}>{name}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{description}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{price}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{categoryId}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{imageUrl}</TableCell>
      <TableCell style={{ textAlign: "center" }}>
        <Button onClick={() => setDataToEdit(el)}>Editar</Button>
        <Button onClick={() => deleteData(name)}>Eliminar</Button>
      </TableCell>
    </TableRow>
  );
};

export default TableRowItems;
