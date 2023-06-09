import { TYPES } from "../actions/shoppingAction";
import { shoppingInitialState } from "./initialState";

interface Product {
  name: string;
  itemId: number;
  price: number;
  category: string;
  imageUrl: string;
  warranty: number;
}

interface CartProduct {
  name: string;
  itemId: number;
  price: number;
  category: string;
  imageUrl: string;
  warranty: number;
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

export const shoppingReducer = (state: ShoppingState, action: Action) => {
  switch (action.type) {
    case TYPES.ADD_TO_CART: {
      const newItem = state.products.find(
        (item: any) => item.itemId === action.payload
      );

      if (!newItem) return state;

      const itemInCart = state.cart.find(
        (item: any) => item.itemId === newItem.itemId
      );

      return itemInCart
        ? {
            ...state,
            cart: state.cart.map((item: any) =>
              item.itemId === newItem.itemId
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
        (item: any) => item.itemId === action.payload
      );

      if (!removeItem) return state;

      return removeItem.quantity > 1
        ? {
            ...state,
            cart: state.cart.map((item: any) =>
              item.itemId === action.payload
                ? { ...item, quantity: item.quantity - 1 }
                : item
            ),
            total: state.total - removeItem.price,
          }
        : {
            ...state,
            cart: state.cart.filter((item) => item.itemId !== action.payload),
            total: state.total - removeItem.price,
          };
    }
    case TYPES.REMOVE_ALL_FROM_CART: {
      const itemToDelete = state.cart.find(
        (item) => item.itemId === action.payload
      );
      if (!itemToDelete) return state;
      return {
        ...state,
        cart: state.cart.filter((item) => item.itemId !== action.payload),
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
