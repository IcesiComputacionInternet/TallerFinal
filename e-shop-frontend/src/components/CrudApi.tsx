import { useEffect, useReducer, useState } from "react";
import { TYPES } from "../actions/crudActions";
import { helpHttp } from "../helpers/helpHttp";
import { crudInitialState, crudReducer } from "../reducers/crudReducer";
import CrudForm from "./CrudForm";
import CrudTable from "./CrudTable";
import Loader from "./Loader";

interface props {
  type: string;
}

//CRUD API CON REDUCER
const CrudApi = ({ type }: props) => {
  const api = helpHttp();
  const url = "/";

  //const [db, setDb] = useState(null);
  const [state, dispatch] = useReducer(crudReducer, crudInitialState);
  const { db } = state;

  const [dataToEdit, setDataToEdit] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  //Cargamos los datos una sola vez, nuestra api inicial
  useEffect(() => {
    setLoading(true);
    helpHttp()
      .get(url, {})
      .then((res) => {
        //Si no hay error entonces hago la insercion al estado
        if (res !== undefined && !res.err) {
          //setDb(res);
          dispatch({ type: TYPES.READ_ALL_DATA, payload: res });
          setError(null);
        } else {
          setError(res);
          //setDb(null);
          dispatch({ type: TYPES.NO_DATA });
        }
      });
    setLoading(false);
  }, [url]);

  const createData = (data: any) => {
    //Le genero un id
    data.id = Date.now();

    //Agrego opciones y la cabecera para que sepa que el contenido de la data sera un json
    const options = {
      body: data,
      headers: {
        "content-type": "application/json",
      },
    };
    //la peticion por si sola hace la eliminacion o metodo correspondiente a dicho endpoint, solo nos quedara cambiar el estado para que se vuelva a graficar
    api.post(url, options).then((res) => {
      //Si no hay error entonces actualiza la base de datos
      if (res !== undefined && !res.err) {
        //setDb([...db, res]);
        dispatch({ type: TYPES.CREATE_DATA, payload: res });
        setError(null);
      } else {
        setError(res);
      }
    });
  };

  const updateData = (data: any) => {
    const endpoint = `${url}/${data.id}`;
    const options = {
      body: data,
      headers: {
        "content-type": "application/json",
      },
    };
    //la peticion por si sola hace la eliminacion o metodo correspondiente a dicho endpoint, solo nos quedara cambiar el estado para que se vuelva a graficar
    api.put(endpoint, options).then((res) => {
      if (res !== undefined && !res.err) {
        //let newData = db.map((el) => (el.id === data.id ? data : el));
        //setDb(newData);
        dispatch({ type: TYPES.UPDATE_DATA, payload: data });
        setError(null);
      } else {
        setError(res);
      }
    });
  };

  const deleteData = (id: any) => {
    //Comprobamos si si desea eliminar
    const isDelete = window.confirm(
      `¿Estas seguro de eliminar el registro con el id:${id}?`
    );

    if (isDelete) {
      //El endpoint del objeto a eliminar
      const endpoint = `${url}/${id}`,
        options = {
          headers: {
            "content-type": "application/json",
          },
        };
      //la peticion por si sola hace la eliminacion o metodo correspondiente a dicho endpoint, solo nos quedara cambiar el estado para que se vuelva a graficar
      api.del(endpoint, options).then((res) => {
        if (res !== undefined && !res.err) {
          //let newData = db.filter((el) => el.id !== id);
          //Cambiamos el estado para que se renderize
          //setDb(newData);
          dispatch({ type: TYPES.DELETE_DATA, payload: id });
        } else {
          setError(res);
        }
      });
    }
  };

  return (
    <div>
      <article className="grid-1-2">
        <CrudForm
          createData={createData}
          updateData={updateData}
          dataToEdit={dataToEdit}
          setDataToEdit={setDataToEdit}
          type={type}
        />
        {/**Cuando haya un error o este cargando se mostrara dicho componente */}
        {loading && <Loader align="center" />}
        {error && (
          <div className="center">
            <p>Error al cargar los datos</p>
          </div>
        )}

        {db && (
          <CrudTable
            data={db}
            deleteData={deleteData}
            setDataToEdit={setDataToEdit}
          />
        )}
      </article>
    </div>
  );
};

export default CrudApi;
