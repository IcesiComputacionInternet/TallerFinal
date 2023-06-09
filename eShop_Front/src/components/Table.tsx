import React from "react";
import "../styles/table.css";


interface TableColumn {
  heading: string;
  value: string;
  render?: (item: any) => React.ReactNode;
}

interface TableProps {
  data: any[];
  columns: TableColumn[]
}

interface TableHeadItemProps {
  item: TableColumn;
}

interface TableRowProps {
  item: any;
  columns: TableColumn[]
}



const Table: React.FC<TableProps> = ({ data, columns}) => {

  return (
    <table>
      <thead>
        <tr>
          {columns.map((column, index) => (
            <TableHeadItem key={index} item={column} />
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
          <TableRow
            key={index}
            item={item}
            columns={columns}
          />
        ))}
      </tbody>
    </table>
  );
};

const TableHeadItem: React.FC<TableHeadItemProps> = ({ item }) => (
  <th>{item.heading}</th>
);

const TableRow: React.FC<TableRowProps> = ({
  item,
  columns
}) => {
  
  return (
    <tr>
      {columns.map((column, index) => (
        <td key={index}>
          {column.render ? column.render(item[column.value]) : item[column.value]}
        </td>
      ))}
      
      
    </tr>
  );
};

export default Table;
