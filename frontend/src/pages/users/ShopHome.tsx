import { useEffect, useState } from "react";
import axios from "axios";
import { Modal, Button, Navbar } from "react-bootstrap";
import { BsCartFill} from "react-icons/bs";
import Logout from "./Logout";
import "../ShopHome.css";
import ReactModal from "react-modal";
import { Link } from "react-router-dom";

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

const ShopHome = () => {
  const [items, setItems] = useState<Item[]>([]);
  const [selectedItem, setSelectedItem] = useState<Item | null>(null);
  const [showModal, setShowModal] = useState(false);
  const [cartItems, setCartItems] = useState<Item[]>([]);
  const [showCartModal, setShowCartModal] = useState(false);

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const response = await axios.get("http://localhost:8091/items", {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt")}`,
          },
        });
        setItems(response.data);
      } catch (error) {
        alert("Error fetching items: "+error);
      }
    };

    fetchItems();
  }, []);

  const handleItemClick = (item: Item) => {
    setSelectedItem(item);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const addItemToCart = () => {
    if (selectedItem && selectedItem.available) {
      setCartItems((prevItems) => [...prevItems, selectedItem]);
      setSelectedItem(null);
      setShowModal(false);
      alert("Producto añadido al carrito");
    }
  };

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
      if (response.status === 201) {
        setCartItems([]);
        alert("Orden realizada con éxito");
      }
    } catch (error) {
      console.error("Error creating order:", error);
      alert("Ocurrió un error al crear la orden");
    }
  };

  return (
    <div>
      <Navbar bg="primary" variant="dark" fixed="top" className="justify-content-between">
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
      <br />
      <div className="title-container">
        <h2 className="mx-auto">Productos de la tienda</h2>
      </div>
      <div className="shop-home-container">
        <div className="item-grid">
          {items.map((item, index) => (
            <div
              key={index}
              className="item"
              onClick={() => handleItemClick(item)}
            >
              <h3>{item.name}</h3>
              <img src={item.imageUrl} alt={item.name} />
              <p className="description">{item.description}</p>
            </div>
          ))}
        </div>
      </div>

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
          <Button variant="secondary" onClick={handleCloseCartModal}>
            Cerrar
          </Button>
        </div>
      </ReactModal>

      {selectedItem && (
        <Modal show={showModal} onHide={handleCloseModal}>
          <Modal.Header closeButton>
            <Modal.Title>{selectedItem.name}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <img
              src={selectedItem.imageUrl}
              alt={selectedItem.name}
              className="modal-image"
            />
            <p><strong>Descripción:</strong> {selectedItem.description}</p>
            <p><strong>Precio:</strong> ${selectedItem.price}</p>
            <p><strong>Voltaje Mínimo:</strong> {selectedItem.minVoltage}</p>
            <p><strong>Voltaje Máximo:</strong> {selectedItem.maxVoltage}</p>
            <p><strong>Fuente de Energía:</strong> {selectedItem.sourceOfEnergy}</p>
            <p><strong>Nivel de Eficiencia:</strong> {selectedItem.levelOfEfficiency}</p>
            <p><strong>Marca:</strong> {selectedItem.marca}</p>
            <p><strong>Modelo:</strong> {selectedItem.model}</p>
            <p><strong>Garantía:</strong> {selectedItem.guarantee}</p>
            <p className={`availability ${selectedItem.available ? 'available' : 'unavailable'}`}>
              <strong>Disponible:</strong> {selectedItem.available ? 'Sí' : 'No'}
            </p>
          </Modal.Body>
          <Modal.Footer>
            <div className="text-center">
              <Button variant="primary" onClick={addItemToCart} disabled={!selectedItem.available}>
                Comprar
              </Button>
            </div>
          </Modal.Footer>
        </Modal>
      )}
    </div>
  );
};

export default ShopHome;