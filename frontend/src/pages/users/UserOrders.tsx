import Orders from "../admin-shop/Orders";
import { Modal, Button, Navbar } from "react-bootstrap";
import ReactModal from "react-modal";
import { Link } from "react-router-dom";
import { BsCartFill} from "react-icons/bs";
import Logout from "../../components/Logout";
import { useEffect, useState } from "react";
import axios from "axios";

interface Item {
    itemId: string;
    description: string;
    name: string;
    price: number;
    imageUrl: string;
    minVoltage: number;
    maxVoltage: number;
    sourceOfEnergy: string;
    levelOfEfficiency: string;
    marca: string;
    model: string;
    guarantee: number;
    available?: boolean;
  }

const UserOrders = () => {
    const [cartItems, setCartItems] = useState<Item[]>([]);
    const [showCartModal, setShowCartModal] = useState(false);

    useEffect(() => {
        
        const loadCartItems= async () => {
        
        var token=localStorage.getItem("jwt");
        if (token) {
            var items=localStorage.getItem("cartItems");
            if(items){
                const cartProds = JSON.parse(items);
                setCartItems(cartProds);

            }
        }
        };
        loadCartItems();
      }, []);

    const handleOpenCartModal = () => {
        setShowCartModal(true);
      };
    
      const handleCloseCartModal = () => {
        setShowCartModal(false);
      };

      const handleOrder = async () => {
        try {
          const response = await axios.post(
            "http://localhost:8091/orders",
            {
              items: cartItems,
            },
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("jwt")}`,
              },
            }
          );
          if (response.status === 200) {
            setCartItems([]);
            alert("Orden realizada con éxito");
            window.location.reload();
          }
        } catch (error) {
          console.error("Error creating order:", error);
          alert("Ocurrió un error al crear la orden");
        }
      };
    
    return (
        <div>
        <Navbar variant="dark" style={{backgroundColor:"#212529"}} fixed="top" className="justify-content-between">
                <div className="container">
                <Navbar.Brand href="/users/home-shop" style={{marginLeft:"200px"}}>
                    <img src="data:image/svg+xml;charset=UTF-8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22white%22%20class%3D%22bi%20bi-bag-heart-fill%22%20viewBox%3D%220%200%2016%2016%22%3E%0A%20%20%3Cpath%20d%3D%22M11.5%204v-.5a3.5%203.5%200%201%200-7%200V4H1v10a2%202%200%200%200%202%202h10a2%202%200%200%200%202-2V4h-3.5ZM8%201a2.5%202.5%200%200%201%202.5%202.5V4h-5v-.5A2.5%202.5%200%200%201%208%201Zm0%206.993c1.664-1.711%205.825%201.283%200%205.132-5.825-3.85-1.664-6.843%200-5.132Z%22%2F%3E%0A%3C%2Fsvg%3E"  alt="Logo" width="25" height="25" className="d-inline-block align-text-top"/>
                    EShop
                    </Navbar.Brand>
                <div className="d-flex align-items-center">
                    <div className="container">
                    <Link to="/orders" className="btn btn-light">Mis órdenes</Link>
                    </div>
                    <div className="cart-icon-container ml-auto" onClick={handleOpenCartModal}>
                    <BsCartFill size={24} />
                    <span className="cart-item-count">{cartItems.length}</span>
                    </div>
                    <Logout />
                </div>
                </div>
            </Navbar>
             <ReactModal isOpen={showCartModal} onRequestClose={handleCloseCartModal} className="cart-modal">
                <h2>Carrito de compras</h2>
                {cartItems.length > 0 ? (
                <ul className="cart-item-list">
                    {cartItems.map((item) => (
                    <li key={item.itemId} className="cart-item">
                        <img src={item.imageUrl} alt={item.name} className="cart-item-image-small" />
                        <div>
                        <p>{item.name}</p>
                        <p>Precio: ${item.price}</p>
                        </div>
                    </li>
                    ))}
                </ul>
                ) : (
                <p>No hay productos en el carrito</p>
                )}
                <div className="text-center">
                <Button variant="success" onClick={handleOrder} disabled={cartItems.length === 0}>
                    Hacer orden
                </Button>
                <Button variant="btn btn-dark" onClick={handleCloseCartModal}>
                    Cerrar
                </Button>
                <Button variant="danger" onClick={() => setCartItems([])} disabled={cartItems.length === 0}>
                    Vaciar Carrito
                </Button>
                </div>
            </ReactModal>
            <Orders/>
        </div>
    );
}
export default UserOrders;