interface Props {
  data: {
    name: string;
    itemId: number;
    price: number;
    description: string;
    category: string;
    imageUrl: string;
    warranty: number;
  };
  addToCart: (id: number) => void;
}

const ProductItem = ({ data, addToCart }: Props) => {
  const { name, itemId, price, category, imageUrl, warranty, description } =
    data;
  return (
    <div className="col-lg-4 col-md-12 mb-4">
      <div className="card">
        <div
          className="bg-image hover-zoom ripple ripple-surface ripple-surface-light"
          data-mdb-ripple-color="light"
        >
          <img src={imageUrl} className="w-100" />
          <a href="#!">
            <div className="mask">
              <div className="d-flex justify-content-start align-items-end h-100">
                <h5>
                  <span className="badge bg-primary ms-2">{category}</span>
                </h5>
              </div>
            </div>
            <div className="hover-overlay">
              <div
                className="mask"
                style={{ backgroundColor: "rgba(251, 251, 251, 0.15)" }}
              ></div>
            </div>
          </a>
        </div>
        <div className="card-body">
          <a href="" className="text-reset">
            <h5 className="card-title mb-3">{name}</h5>
          </a>
          <a href="" className="text-reset">
            <p>{description}</p>
          </a>
          <h6 className="mb-3">${price}.00</h6>
        </div>
        <button
          className="btn btn-outline-primary"
          onClick={() => {
            addToCart(itemId);
          }}
        >
          Añadir al carrito
        </button>
      </div>
    </div>
  );
};

export default ProductItem;
