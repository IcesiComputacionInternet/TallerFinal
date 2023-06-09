import { useEffect, useState } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import ItemCart from "./components/ItemCart";
import { postOrder } from "../../services/orders";

interface Props {
    setInfoToast: (message: string, title: string) => void;
}

interface CartItem {
    itemId: string;
    name: string;
    description: string;
    price: number;
    imageUrl: string;
    category: {
        name: string;
        description: string;
    }
}

function Cart ({setInfoToast}: Props) {
    const [cartItems, setCartItems] = useState<CartItem[]>([]);
    const navigation: NavigateFunction = useNavigate();

    useEffect(() => {
        const cart = localStorage.getItem("cart");
        if(cart){
            setCartItems(JSON.parse(cart));
        }
    }, []);

    const handleRemoveFromCart = (itemId:string) => {
        const itemIndex = cartItems.findIndex((item) => item.itemId === itemId);
        if (itemIndex !== -1) {
            const updatedCartItems = [...cartItems];
            updatedCartItems.splice(itemIndex, 1);
            setCartItems(updatedCartItems);
            localStorage.setItem("cart", JSON.stringify(updatedCartItems));
            setInfoToast('Item removed from cart', 'Success');
        }
    };

    const handleCompletePurchase = () => {
        const userId = localStorage.getItem("userId");
        if(userId){
            console.log(cartItems.map(item => item.itemId));
            console.log(userId);
            postOrder(userId, cartItems.map(item => item.itemId)).then(() => {
                setCartItems([]);
                localStorage.removeItem("cart");
                setInfoToast('Purchase completed successfully', 'Success');
            }).catch((error) => {
                setInfoToast('Error completing purchase', 'Error');
                console.log(error);
            });
        }
        setInfoToast('You must be logged in to complete the purchase', 'Warning');
    }

    const goOrders = () => {
        navigation('/orders');
    }

    return (
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Cart</h1>
                {cartItems[0] ? (
          <>
            {cartItems.map((item: CartItem) => (
                <ItemCart
                    key={item.itemId}
                    item={item}
                    handleRemoveFromCart={handleRemoveFromCart}
                />
                ))}
                <button className="btn btn-primary mt-3 my-2" onClick={handleCompletePurchase}>
                    Complete Purchase
                </button>
            </>
            ): 
            <>
                <h3 className="text-center">Your cart is empty</h3>
                <button className="btn btn-primary mt-3 my-2" onClick={goOrders}>
                    Go to orders
                </button>
            </>}
                </div>
        </div>
    )
}

export default Cart;