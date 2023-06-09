import CrudTableRow from "./CrudTableRow";

interface Props {
  data: any;
  type: string;
  setDataToEdit: any;
  deleteData: any;
}
const CrudTable = ({ data, setDataToEdit, deleteData, type }: Props) => {
  console.log(data);
  return (
    <div>
      {type === "PRODUCTS" && (
        <>
          <h3>Tabla de Productos</h3>
          <table>
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Descripcion</th>
                <th>Precio</th>
                <th>Garantia</th>
              </tr>
            </thead>
            <tbody>
              {data.length > 0 && type === "PRODUCTS" ? (
                data.map((el: any) => (
                  <CrudTableRow
                    el={el}
                    key={el.itemId}
                    setDataToEdit={setDataToEdit}
                    deleteData={deleteData}
                    type={type}
                  />
                ))
              ) : (
                <tr>
                  <td colSpan={3}>Sin datos</td>
                </tr>
              )}
            </tbody>
          </table>
        </>
      )}

      {type === "CATEGORIES" && (
        <>
          <h3>Tabla de Categorias</h3>
          <table>
            <thead>
              <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Descripcion</th>
              </tr>
            </thead>
            <tbody>
              {data.length > 0 && type === "CATEGORIES" ? (
                data.map((el: any) => (
                  <CrudTableRow
                    el={el}
                    key={el.categoryId}
                    setDataToEdit={setDataToEdit}
                    deleteData={deleteData}
                    type={type}
                  />
                ))
              ) : (
                <tr>
                  <td colSpan={3}>Sin datos</td>
                </tr>
              )}
            </tbody>
          </table>
        </>
      )}

      {type === "USERS" && (
        <>
          <h3>Tabla de Usuarios</h3>
          <table>
            <thead>
              <tr>
                <th>Email</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Telefono</th>
                <th>Direccion</th>
              </tr>
            </thead>
            <tbody>
              {data.length > 0 && type === "USERS" ? (
                data.map((el: any) => (
                  <CrudTableRow
                    el={el}
                    key={el.userId}
                    setDataToEdit={setDataToEdit}
                    deleteData={deleteData}
                    type={type}
                  />
                ))
              ) : (
                <tr>
                  <td colSpan={3}>Sin datos</td>
                </tr>
              )}
            </tbody>
          </table>
        </>
      )}

      {type === "ROLES" && (
        <>
          <h3>Tabla de Productos</h3>
          <table>
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Constelacion</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {data.length > 0 && type === "ROLES" ? (
                data.map((el: any) => (
                  <CrudTableRow
                    el={el}
                    key={el.roleId}
                    setDataToEdit={setDataToEdit}
                    deleteData={deleteData}
                    type={type}
                  />
                ))
              ) : (
                <tr>
                  <td colSpan={3}>Sin datos</td>
                </tr>
              )}
            </tbody>
          </table>
        </>
      )}

      {type === "ORDERS" && (
        <>
          <h3>Tabla de Productos</h3>
          <table>
            <thead>
              <tr>
                <th>Id</th>
                <th>UserId</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              {data.length > 0 && type === "ORDERS" ? (
                data.map((el: any) => (
                  <CrudTableRow
                    el={el}
                    key={el.orderId}
                    setDataToEdit={setDataToEdit}
                    deleteData={deleteData}
                    type={type}
                  />
                ))
              ) : (
                <tr>
                  <td colSpan={3}>Sin datos</td>
                </tr>
              )}
            </tbody>
          </table>
        </>
      )}
    </div>
  );
};

export default CrudTable;
