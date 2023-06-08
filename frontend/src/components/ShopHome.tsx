import { useEffect, useState } from "react";
import axios from "axios";
import { Modal, Button, Navbar } from "react-bootstrap";
import { BsCartFill, BsTrash } from "react-icons/bs";
import Logout from "./Logout";
import "../ShopHome.css";
import ReactModal from "react-modal";

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
  available: boolean;
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
        console.error("Error fetching items:", error);
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

  const handleClearCart = () => {
    if (window.confirm("¿Estás seguro de eliminar todos los productos del carrito?")) {
      setCartItems([]);
      alert("Se han eliminado todos los productos del carrito");
    }
  };

  return (
    <div>
      <Navbar bg="primary" variant="dark" fixed="top" className="justify-content-between">
        <Navbar.Brand href="#home">Mi Tienda</Navbar.Brand>
        <div className="d-flex align-items-center ml-auto">
          <div className="cart-icon-container mr-3" onClick={handleOpenCartModal}>
            <BsCartFill size={24} />
            <span className="cart-item-count">{cartItems.length}</span>
          </div>
          <Logout />
        </div>
      </Navbar>
      <div className="title-container">
        <h2 className="mx-auto">Productos de la tienda</h2>
      </div>
      <div className="shop-home-container">
        <div className="item-grid">
          {items.map((item) => (
            <div
              key={item.itemId}
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
          <Button variant="danger" onClick={handleClearCart} disabled={cartItems.length === 0}>
            <BsTrash /> Eliminar todos los productos
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