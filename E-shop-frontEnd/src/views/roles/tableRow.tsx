import React from "react";
import { TableRow, TableCell, Button } from "@mui/material";

const TableRowItems = ({ el, setDataToEdit, deleteData }) => {
  let { roleName, description } = el;
  return (
    <TableRow sx={{ textAlgin: "center" }}>
      <TableCell>{roleName}</TableCell>
      <TableCell>{description}</TableCell>
      <TableCell>
        <Button onClick={() => setDataToEdit(el)}>Editar</Button>
        <Button onClick={() => deleteData(name)}>Eliminar</Button>
      </TableCell>
    </TableRow>
  );
};

export default TableRowItems;
