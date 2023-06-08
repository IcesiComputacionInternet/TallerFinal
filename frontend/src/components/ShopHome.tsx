import { useEffect, useState } from "react";
import axios from "axios";
import { Modal, Button, Navbar } from "react-bootstrap";
import { BsCartFill } from "react-icons/bs";
import Logout from "./Logout";
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
      setCartItemCount((prevCount) => prevCount + 1); // Incrementar la cantidad de productos en el carrito
      setShowModal(false); // Cerrar el modal después de agregar el producto al carrito
      alert("Compra agregada exitosamente al carrito"); // Mostrar mensaje de éxito
    } catch (error) {
      console.error("Error adding item to orders:", error);
    }
  };

  return (
    <div>
      <Navbar bg="dark" variant="dark" fixed="top" className="justify-content-between">
        <Navbar.Brand href="/home-shope">Mi Tienda</Navbar.Brand>
        <div className="d-flex align-items-center ml-auto">
          <div className="cart-icon-container mr-3">
            <BsCartFill size={24} />
            <span className="cart-item-count">{cartItemCount}</span>
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
            <div className="text-center"> {/* Contenedor para centrar el botón */}
              <Button variant="primary" onClick={addItemToOrders}>
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