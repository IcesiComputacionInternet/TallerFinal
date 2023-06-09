import NavBar from "../components/NavBar";

const CreateItem = () => {
  return (
    <div>
      <NavBar role="SHOP" />
      <br />
      <br />
      <br />
      <h3>Agregar Nuevo Producto</h3>
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
    </div>
  );
};

export default CreateItem;
