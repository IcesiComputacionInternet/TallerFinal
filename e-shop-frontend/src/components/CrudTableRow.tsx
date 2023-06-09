interface CrudTableRowProps {
  el: {
    name: string;
    constellation: string;
    id: number;
  };
  setDataToEdit: any;
  deleteData: any;
}

const CrudTableRow = ({ el, setDataToEdit, deleteData }: CrudTableRowProps) => {
  const { name, constellation, id } = el;
  return (
    <tr>
      <td>{name}</td>
      <td>{constellation}</td>
      <td>
        <button
          onClick={() => {
            setDataToEdit(el);
          }}
        >
          Editar
        </button>
        <button onClick={() => deleteData(id)}>Eliminar</button>
      </td>
    </tr>
  );
};

export default CrudTableRow;
