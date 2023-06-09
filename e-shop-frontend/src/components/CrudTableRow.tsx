interface CrudTableRowProps {
  el: any;
  setDataToEdit: any;
  deleteData: any;
  type: string;
}

const CrudTableRow = ({
  el,
  setDataToEdit,
  deleteData,
  type,
}: CrudTableRowProps) => {
  return (
    <>
      {type === "PRODUCTS" && (
        <tr>
          <td>{el.name}</td>
          <td>{el.description}</td>
          <td>{el.price}</td>
          <td>{el.warranty}</td>
          <td>
            <button
              onClick={() => {
                setDataToEdit(el);
              }}
            >
              Editar
            </button>
            <button onClick={() => deleteData(el.itemId)}>Eliminar</button>
          </td>
        </tr>
      )}
      {type === "ORDERS" && (
        <tr>
          <td>{el.orderId}</td>
          <td>{el.userId}</td>
          <td>{el.total}</td>
          <td>{el.orderStatus}</td>
          <td>
            <button
              onClick={() => {
                setDataToEdit(el);
              }}
            >
              Editar
            </button>
            <button onClick={() => deleteData(el.itemId)}>Eliminar</button>
          </td>
        </tr>
      )}
      {type === "CATEGORIES" && (
        <tr>
          <td>{el.name}</td>
          <td>{el.description}</td>
          <td>
            <button
              onClick={() => {
                setDataToEdit(el);
              }}
            >
              Editar
            </button>
            <button onClick={() => deleteData(el.itemId)}>Eliminar</button>
          </td>
        </tr>
      )}
      {type === "USERS" && (
        <tr>
          <td>{el.email}</td>
          <td>{el.firstname}</td>
          <td>{el.lastname}</td>
          <td>{el.phoneNumber}</td>
          <td>{el.address}</td>
          <td>
            <button
              onClick={() => {
                setDataToEdit(el);
              }}
            >
              Editar
            </button>
            <button onClick={() => deleteData(el.itemId)}>Eliminar</button>
          </td>
        </tr>
      )}
      {type === "ROLES" && (
        <tr>
          <td>{el.name}</td>
          <td>{el.description}</td>
          <td>
            <button
              onClick={() => {
                setDataToEdit(el);
              }}
            >
              Editar
            </button>
            <button onClick={() => deleteData(el.itemId)}>Eliminar</button>
          </td>
        </tr>
      )}
    </>
  );
};

export default CrudTableRow;
