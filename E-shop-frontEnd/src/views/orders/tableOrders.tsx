import { Table, TableContainer, TableHead, TableRow } from "@mui/material";
import { TableCell, TableBody, Paper } from "@mui/material";
import React from "react";
import TableRowOrder from "./tableRowOrders";

interface IProps {
  data: any[];
  setDataToEdit: any;
}

const TableOrder: React.FC<IProps> = ({ data, setDataToEdit }) => {
  return (
    <TableContainer
      component={Paper}
      sx={{ width: "80%", margin: "60px auto" }}
    >
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow sx={{ textAlgin: "center" }}>
            <TableCell style={{ textAlign: "center" }}>
              <b>Usuario</b>
            </TableCell>
            <TableCell style={{ textAlign: "center" }}>
              <b>Estado</b>
            </TableCell>
            <TableCell style={{ textAlign: "center" }}>
              <b>Total</b>
            </TableCell>
            <TableCell style={{ textAlign: "center" }}>
              <b>Items</b>
            </TableCell>
            <TableCell style={{ textAlign: "center" }}>
              <b>Opciones</b>
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.length === 0 ? (
            <TableRow sx={{ textAlgin: "center" }}>
              <TableCell scope="3" style={{ textAlign: "center" }}>
                Sin Datos
              </TableCell>
            </TableRow>
          ) : (
            data.map((el: any) => (
              <TableRowOrder
                key={el.userEmail}
                el={el}
                setDataToEdit={setDataToEdit}
              />
            ))
          )}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default TableOrder;
