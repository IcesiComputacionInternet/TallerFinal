import React from "react";
import { TableRow, TableCell, Button } from "@mui/material";

interface IProps {
  el: any;
  setDataToEdit: any;
  deleteData: any;
}

const TableRowItems: React.FC<IProps> = ({ el, setDataToEdit, deleteData }) => {
  let { roleName, description } = el;
  let test: string = el.roleName;
  return (
    <TableRow sx={{ textAlgin: "center" }}>
      <TableCell style={{ textAlign: "center" }}>{roleName}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{description}</TableCell>
      <TableCell style={{ textAlign: "center" }}>
        <Button onClick={() => setDataToEdit(el)}>Editar</Button>
        <Button onClick={() => deleteData(test)}>Eliminar</Button>
      </TableCell>
    </TableRow>
  );
};

export default TableRowItems;
