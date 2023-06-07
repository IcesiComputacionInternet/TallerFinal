import { useEffect, useState } from "react";
import axios from "axios";
import { Modal, Button } from "react-bootstrap";
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

  return (
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
          </div>
        ))}
      </div>

      {selectedItem && (
        <Modal show={showModal} onHide={handleCloseModal}>
          <Modal.Header closeButton>
            <Modal.Title>{selectedItem.name}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>Description: {selectedItem.description}</p>
            <p>Price: ${selectedItem.price}</p>
            <p>Min Voltage: {selectedItem.minVoltage}</p>
            <p>Max Voltage: {selectedItem.maxVoltage}</p>
            <p>Source of Energy: {selectedItem.sourceOfEnergy}</p>
            <p>Level of Efficiency: {selectedItem.levelOfEfficiency}</p>
            <p>Marca: {selectedItem.marca}</p>
            <p>Model: {selectedItem.model}</p>
            <p>Guarantee: {selectedItem.guarantee}</p>
            <p>Available: {selectedItem.available ? "Yes" : "No"}</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseModal}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
      )}
    </div>
  );
};

export default ShopHome;