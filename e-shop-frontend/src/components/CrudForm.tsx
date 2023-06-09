import React, { useState, useEffect } from "react";

interface initialForm {
  setDataToEdit: any;
  dataToEdit: any;
  createData: any;
  updateData: any;
  type: string;
}

interface typeForm {
  type: string;
}
const data = {
  name: "",
  constellation: "",
  id: null,
};
const CrudForm = ({
  setDataToEdit,
  dataToEdit,
  createData,
  updateData,
  type,
}: initialForm) => {
  const [form, setForm] = useState(data);

  useEffect(() => {
    if (dataToEdit) {
      setForm(dataToEdit);
    } else {
      setForm(data);
    }
  }, [dataToEdit]);

  const handleChange = (e: any) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();

    if (!form.name || !form.constellation) {
      alert("Datos incompletos");
      return;
    }

    //Si es nulo, significa que hara una inserccion, sino la actualizara
    if (form.id === null) {
      createData(form);
    } else {
      updateData(form);
    }

    handleReset();
  };

  const handleReset = (e?: any) => {
    //Devolvemos el estado a los valores iniciales
    setForm(data);
    // Limpiados la informacion
    setDataToEdit(null);
  };

  return (
    <div>
      {!dataToEdit ? <h3>Agregar</h3> : <h3>Editar</h3>}
      {type === "ORDER" && (
        <form className="row">
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              ID del usuario
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              aria-describedby="emailHelp"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="exampleInputPassword1" className="form-label">
              Estado de la orden
            </label>
            <input
              type="password"
              className="form-control"
              id="exampleInputPassword1"
            />
          </div>
          <button type="submit" className="btn btn-primary">
            Enviar
          </button>
        </form>
      )}
      {type === "PRODUCT" && (
        <form>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Nombre
            </label>
            <input
              type="email"
              className="form-control"
              id="name"
              aria-describedby="emailHelp"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="price" className="form-label">
              Precio
            </label>
            <input type="text" className="form-control" id="price" />
          </div>
          <div className="mb-3">
            <label htmlFor="imgUrl" className="form-label">
              Url imagen
            </label>
            <input type="text" className="form-control" id="imgUrl" />
          </div>
          <div className="mb-3">
            <label htmlFor="description" className="form-label">
              Descripcion
            </label>
            <input type="text" className="form-control" id="description" />
          </div>
          <div className="mb-3">
            <label htmlFor="category" className="form-label">
              Categoria
            </label>
            <input type="text" className="form-control" id="category" />
          </div>
          <div className="mb-3">
            <label htmlFor="warranty" className="form-label">
              Garantia
            </label>
            <input type="date" className="form-control" id="warranty" />
          </div>

          <button type="submit" className="btn btn-primary">
            Enviar
          </button>
        </form>
      )}
      {type === "ROLES" && (
        <form>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Nombre
            </label>
            <input
              type="email"
              className="form-control"
              id="name"
              aria-describedby="emailHelp"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="description" className="form-label">
              Descripcion
            </label>
            <input type="password" className="form-control" id="description" />
          </div>
          <button type="submit" className="btn btn-primary">
            Enviar
          </button>
        </form>
      )}
      {type === "USER" && (
        <form>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Nombre
            </label>
            <input type="text" className="form-control" id="name" />
          </div>
          <div className="mb-3">
            <label htmlFor="lastname" className="form-label">
              Apellido
            </label>
            <input type="text" className="form-control" id="lastname" />
          </div>
          <div className="mb-3">
            <label htmlFor="email1" className="form-label">
              Correo electrónico
            </label>
            <input type="email" className="form-control" id="email1" />
          </div>
          <div className="mb-3">
            <label htmlFor="phoneNumber" className="form-label">
              Telefono
            </label>
            <input type="tel" className="form-control" id="phoneNumber" />
          </div>
          <div className="mb-3">
            <label htmlFor="address" className="form-label">
              Direccion
            </label>
            <input type="text" className="form-control" id="address" />
          </div>
          <div className="mb-3">
            <label htmlFor="birthdate" className="form-label">
              Fecha de nacimiento
            </label>
            <input type="date" className="form-control" id="birthdate" />
          </div>
          <div className="mb-3">
            <label htmlFor="role" className="form-label">
              Mi rol es:
            </label>
            <div className="row">
              <div className="form-check col">
                <input
                  className="form-check-input"
                  type="radio"
                  name="flexRadioDefault"
                  id="userRole"
                />
                <label className="form-check-label" htmlFor="userRole">
                  Usuario
                </label>
              </div>
              <div className="form-check col">
                <input
                  className="form-check-input"
                  type="radio"
                  name="flexRadioDefault"
                  id="shopRole"
                  checked
                />
                <label className="form-check-label" htmlFor="shopRole">
                  Tienda
                </label>
              </div>
              <div className="form-check col">
                <input
                  className="form-check-input"
                  type="radio"
                  name="flexRadioDefault"
                  id="flexRadioDefault2"
                  checked
                />
                <label className="form-check-label" htmlFor="adminRole">
                  Administrador
                </label>
              </div>
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">
              Contraseña
            </label>
            <input type="password" className="form-control" id="password" />
          </div>
          <button type="submit" className="btn btn-primary">
            Enviar
          </button>
        </form>
      )}
      {type === "CATEGORY" && (
        <form>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Nombre
            </label>
            <input
              type="email"
              className="form-control"
              id="name"
              aria-describedby="emailHelp"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="description" className="form-label">
              Descripcion
            </label>
            <input type="password" className="form-control" id="description" />
          </div>
          <button type="submit" className="btn btn-primary">
            Enviar
          </button>
        </form>
      )}
    </div>
  );
};

export default CrudForm;
