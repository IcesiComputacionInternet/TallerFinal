import { Table, TableContainer, TableHead, TableRow } from "@mui/material";
import { TableCell, TableBody, Paper } from "@mui/material";
import React from "react";
import TableRowItems from "./tableRow";

interface IProps {
  data: any[];
  setDataToEdit: any;
  deleteData: any;
}

const TableRoles: React.FC<IProps> = ({ data, setDataToEdit, deleteData }) => {
  console.log(data);
  return (
    <TableContainer
      component={Paper}
      sx={{ width: "80%", margin: "60px auto" }}
    >
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow sx={{ textAlgin: "center" }}>
            <TableCell>Nombre del rol</TableCell>
            <TableCell align="right">Descripcion</TableCell>
            <TableCell align="right">Opciones</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.length === 0 ? (
            <TableRow sx={{ textAlgin: "center" }}>
              <TableCell colSpan="3">Sin Datos</TableCell>
            </TableRow>
          ) : (
            data.map((el: any) => (
              <TableRowItems
                key={el.roleName}
                el={el}
                setDataToEdit={setDataToEdit}
                deleteData={deleteData}
              />
            ))
          )}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default TableRoles;
