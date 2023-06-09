interface Props {
  data: {
    name: string;
    itemId: number;
    price: number;
    category: string;
    imageUrl: string;
    warranty: number;
    quantity: number;
  };
  delFromCart: (id: number, all?: boolean) => void;
}
const CartItem = ({ data, delFromCart }: Props) => {
  const { name, itemId, price, quantity } = data;
  console.log(data);
  return (
    <div style={{ borderBottom: "thin solid gray", padding: "1rem" }}>
      <h5>
        {name}ˣ<small>{quantity}</small>
      </h5>
      <h6 className="col">c/u:${price}.00</h6>
      <h6 className="col-md-2">
        <i>c/t:${price * quantity}.00</i>
      </h6>
      <div className="row">
        <button
          className="btn btn-outline-primary col-md-6 btn-sm"
          onClick={() => {
            delFromCart(itemId);
          }}
        >
          Eliminar unidad
        </button>
        <button
          className="btn btn-outline-primary col-md-6 btn-sm"
          onClick={() => {
            delFromCart(itemId, true);
          }}
        >
          Eliminar todo
        </button>
      </div>
      <br />
      <br />
    </div>
  );
};

export default CartItem;
