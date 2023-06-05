import React from "react";
import { TableRow, TableCell, Button } from "@mui/material";

interface IProps {
  el: any;
  setDataToEdit: any;
  deleteData: any;
}

const TableRowItems: React.FC<IProps> = ({ el, setDataToEdit, deleteData }) => {
  let { email, phoneNumber, firstName, lastName, address, birthDate, role } =
    el;
  return (
    <TableRow sx={{ textAlgin: "center" }}>
      <TableCell style={{ textAlign: "center" }}>{email}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{phoneNumber}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{firstName}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{lastName}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{address}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{birthDate}</TableCell>
      <TableCell style={{ textAlign: "center" }}>{role}</TableCell>
      <TableCell style={{ textAlign: "center" }}>
        <Button onClick={() => setDataToEdit(el)}>Editar</Button>
        <Button onClick={() => deleteData(email)}>Eliminar</Button>
      </TableCell>
    </TableRow>
  );
};

export default TableRowItems;
