import NavBar from "../components/NavBar";
import { useEffect, useState } from "react";

import helpHttp from "../helpers/helpHttp";
import ShoppingCart from "../components/ShoppingCart";

interface CartProduct {
  name: string;
  itemId: number;
  price: number;
  category: string;
  imageUrl: string;
  warranty: number;
  quantity: number;
}

interface Props {
  role: string;
  setCart: (value: CartProduct[]) => void;
  cart: CartProduct[];
}

const Home = ({ role, setCart, cart }: Props) => {
  const [accounts, setAccounts] = useState([]) as any;
  useEffect(() => {
    const fectchData = async () => {
      const api = helpHttp();
      const options = {
        headers: { Authorization: `Bearer ${localStorage.getItem("jwt")}` },
      };
      api
        .get(`/users/${sessionStorage.getItem("username")}`, options)
        .then((res: { token: string }) => {
          console.log(res);
          if (res) {
            setAccounts(res);
          } else {
            alert("The user has no accounts");
          }
        });
    };
    fectchData();
  }, []);

  return (
    <div className="row">
      <NavBar role="SHOP" />
      <main>
        <br />
        <br />
        <ShoppingCart setCart={setCart} cartApp={cart} />
      </main>
    </div>
  );
};

export default Home;
