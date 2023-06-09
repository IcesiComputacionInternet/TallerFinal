import { NavigateFunction, useNavigate } from "react-router-dom";
import CartToBuyItem from "../components/CartToBuyItem";

interface CartProduct {
  name: string;
  id: number;
  price: number;
  quantity: number;
}

interface Props {
  cart: CartProduct[];
  setCart: (value: CartProduct[]) => void;
}

const BuyPage = ({ cart, setCart }: Props) => {
  const navigation: NavigateFunction = useNavigate();
  let total = 0;
  cart.map((product: any) => {
    total += product.price * product.quantity;
  });

  return (
    <div>
      <h1>Buy Page</h1>
      <div className="row align-items-start">
        {cart.map((product: any) => {
          return <CartToBuyItem key={product.itemId} data={product} />;
        })}
      </div>
      <div className="row align-items-start">
        <h3 className="col">🛒</h3>
        {total !== 0 && <h4>Total:${total}.00</h4>}
      </div>
      <button
        className="btn btn-outline-primary"
        onClick={() => navigation("/")}
      >
        Atras
      </button>
      <button
        className="btn btn-outline-primary"
        onClick={() => navigation("/")}
      >
        Comprar
      </button>
    </div>
  );
};

export default BuyPage;
