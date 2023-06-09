import { TYPES } from "../actions/shoppingAction";

interface Product {
  id: number;
  name: string;
  price: number;
}

interface CartProduct {
  id: number;
  name: string;
  price: number;
  quantity: number;
}

interface ShoppingState {
  products: Product[];
  cart: CartProduct[];
  total: number;
}

interface Action {
  type: string;
  payload?: number;
}

export const shoppingInitialState = {
  products: [
    { id: 1, name: "Producto 1", price: 100 },
    { id: 2, name: "Producto 2", price: 200 },
    { id: 3, name: "Producto 3", price: 150 },
    { id: 4, name: "Producto 4", price: 100 },
    { id: 5, name: "Producto 5", price: 120 },
    { id: 6, name: "Producto 6", price: 100 },
    { id: 7, name: "Producto 7", price: 300 },
    { id: 8, name: "Producto 8", price: 100 },
    { id: 9, name: "Producto 9", price: 175 },
    { id: 10, name: "Producto 10", price: 150 },
  ],
  cart: [],
  total: 0,
};

export const shoppingReducer = (state: ShoppingState, action: Action) => {
  switch (action.type) {
    case TYPES.ADD_TO_CART: {
      const newItem = state.products.find(
        (product: { id: any }) => product.id === action.payload
      );

      if (!newItem) return state;

      const itemInCart = state.cart.find(
        (item: { id: any }) => item.id === newItem.id
      );

      return itemInCart
        ? {
            ...state,
            cart: state.cart.map((item: any) =>
              item.id === newItem.id
                ? { ...item, quantity: item.quantity + 1 }
                : item
            ),
            total: state.total + itemInCart.price,
          }
        : {
            ...state,
            cart: [...state.cart, { ...newItem, quantity: 1 }],
            total: state.total + newItem.price,
          };
    }
    case TYPES.REMOVE_ONE_FROM_CART: {
      const removeItem = state.cart.find(
        (item: any) => item.id === action.payload
      );

      if (!removeItem) return state;

      return removeItem.quantity > 1
        ? {
            ...state,
            cart: state.cart.map((item: any) =>
              item.id === action.payload
                ? { ...item, quantity: item.quantity - 1 }
                : item
            ),
            total: state.total - removeItem.price,
          }
        : {
            ...state,
            cart: state.cart.filter(
              (item: { id: any }) => item.id !== action.payload
            ),
            total: state.total - removeItem.price,
          };
    }
    case TYPES.REMOVE_ALL_FROM_CART: {
      const itemToDelete = state.cart.find(
        (item: { id: any }) => item.id === action.payload
      );
      if (!itemToDelete) return state;
      return {
        ...state,
        cart: state.cart.filter(
          (item: { id: any }) => item.id !== action.payload
        ),
        total: state.total - itemToDelete.price * itemToDelete.quantity,
      };
    }
    case TYPES.CLEAR_CART:
      console.log("clear cart");
      return shoppingInitialState;
    default:
      return state;
  }
};
