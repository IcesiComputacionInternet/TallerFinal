import CrudTableRow from "./CrudTableRow";

interface Props {
  data: any;
  setDataToEdit: any;
  deleteData: any;
}
const CrudTable = ({ data, setDataToEdit, deleteData }: Props) => {
  return (
    <div>
      <h3>Tabla de Datos</h3>
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Constelacion</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((el: any) => (
              <CrudTableRow
                el={el}
                key={el.id}
                setDataToEdit={setDataToEdit}
                deleteData={deleteData}
              />
            ))
          ) : (
            <tr>
              <td colSpan={3}>Sin datos</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default CrudTable;
