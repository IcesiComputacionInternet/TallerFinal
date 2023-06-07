import { useEffect, useState } from "react";
import axios from "axios";
import { Modal, Button, Navbar } from "react-bootstrap";
import { BsCartFill } from "react-icons/bs";
import Logout from "./Logout";
import Purchases from "./Purchases";
import "../ShopHome.css";

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
  const [showPurchasesModal, setShowPurchasesModal] = useState(false);
  const [cartItemCount, setCartItemCount] = useState(0); // Estado para la cantidad de productos en el carrito

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

  const addItemToOrders = async () => {
    try {
      // Handle success response if needed
      setCartItemCount((prevCount) => prevCount + 1); // Incrementar la cantidad de productos en el carrito
      setShowModal(false); // Cerrar el modal después de agregar el producto al carrito
      alert("Compra agregada exitosamente"); // Mostrar mensaje de éxito
    } catch (error) {
      console.error("Error adding item to orders:", error);
    }
  };

  return (
    <div>
      <Navbar bg="dark" variant="dark" fixed="top">
        <Navbar.Brand href="#home">Mi Tienda</Navbar.Brand>
        <div className="ml-auto">
          <Logout />
          <div className="cart-icon-container">
            <BsCartFill size={24} />
            <span className="cart-item-count">{cartItemCount}</span> {/* Mostrar la cantidad de productos en el carrito */}
          </div>
        </div>
      </Navbar>
      <div className="title-container">
        <h1 className="mx-auto">Productos de la tienda</h1>
      </div>
      <div className="shop-home-container">
        <div className="item-grid">
          {items.map((item) => (
            <div
              key={item.itemId}
              className={`item ${selectedItem === item ? "selected" : ""}`}
              onClick={() => handleItemClick(item)}
            >
              <h3>{item.name}</h3>
              <img src={item.imageUrl} alt={item.name} />
              <p className="description">{item.description}</p>
            </div>
          ))}
        </div>
      </div>

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
            <Button variant="primary" onClick={addItemToOrders}>
              Comprar
            </Button>
          </Modal.Footer>
        </Modal>
      )}

      <Purchases show={showPurchasesModal} onHide={() => setShowPurchasesModal(false)} />
    </div>
  );
};

export default ShopHome;