import { useEffect, useReducer, useState } from "react";
import { TYPES } from "../actions/shoppingAction";
import { shoppingReducer } from "../reducers/shoppingReducer";
import "../helpers/helpHttp";
import CartItem from "./CartItem";
import ProductItem from "./ProductItem";
import { NavigateFunction, useNavigate } from "react-router-dom";
import helpHttp from "../helpers/helpHttp";
import { shoppingInitialState } from "../reducers/initialState";

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
  cartApp: CartProduct[];
  setCart: (value: CartProduct[]) => void;
}

const ShoppingCart = ({ setCart, cartApp }: Props) => {
  helpHttp()
    .get("/items/getAll", {})
    .then((res) => {
      shoppingInitialState.products = res;
    });
  const [state, dispatch] = useReducer(shoppingReducer, shoppingInitialState);
  const { products, cart, total } = state;
  const navigation: NavigateFunction = useNavigate();
  useEffect(() => {
    if (cartApp.length > 0) {
      state.cart = cartApp;
      state.total = cartApp.reduce(
        (acc: number, el: CartProduct) => acc + el.price * el.quantity,
        0
      );
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const addToCart = (id: any) => {
    dispatch({ type: TYPES.ADD_TO_CART, payload: id });
  };
  const delFromCart = (id: any, all = false) => {
    if (all) {
      dispatch({ type: TYPES.REMOVE_ALL_FROM_CART, payload: id });
    } else {
      dispatch({ type: TYPES.REMOVE_ONE_FROM_CART, payload: id });
    }
  };
  const clearCart = () => {
    dispatch({ type: TYPES.CLEAR_CART });
  };

  return (
    <>
      <div className="container">
        <div className="row align-items-start">
          <div className="col-md-10">
            <div className="row align-items-start">
              {products.map((product: any) => {
                return (
                  <ProductItem
                    key={product.itemId}
                    data={product}
                    addToCart={addToCart}
                  />
                );
              })}
            </div>
          </div>
          <div className="col-md-2">
            <h3>🛒</h3>
            {total !== 0 && <h4>Total:${total}.00</h4>}
            <div className="row">
              {cart.length > 0 && (
                <button
                  className="btn btn-outline-primary mg-2"
                  onClick={() => {
                    setCart(cart);
                    navigation("/buy");
                  }}
                >
                  Comprar
                </button>
              )}
              {cart.length > 0 && (
                <button
                  className="btn btn-outline-secondary"
                  onClick={async () => {
                    state.cart = [];
                    state.total = 0;
                    setCart([]);
                    clearCart();
                  }}
                >
                  Limpiar Carrito
                </button>
              )}
            </div>
            {cart.map((product: any, index: any) => {
              return (
                <CartItem
                  key={index}
                  data={product}
                  delFromCart={delFromCart}
                />
              );
            })}
          </div>
        </div>
      </div>
    </>
  );
};

export default ShoppingCart;
