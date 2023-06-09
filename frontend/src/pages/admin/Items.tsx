import { useEffect, useState } from "react";
import axios from "axios";
import AdminNavbar from "../../components/AdminNavbar";
import { Modal, Button } from "react-bootstrap";

const baseUrl = "http://localhost:8091";

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

const Items = () => {

  const [items, setItems] = useState<Item[]>([]);
  const [selectedItem, setSelectedItem] = useState<Item | null>(null);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    async function getData() {
      const resultItems = await getItems();
      setItems(resultItems);
    }

    getData();
  }, []);

  const handleItemClick = (item: Item) => {
    setSelectedItem(item);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <div>
     <AdminNavbar />
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
        </Modal>
      )}
    </div>
  );
};

async function getItems(){

  const items = await axios.get(
    baseUrl+"/items",
    {
      headers:{
        "Access-Control-Allow-Origin": baseUrl,
        "MediaType" : "application/json",
        "Authorization":"Bearer "+localStorage.getItem('jwt')
      }
    }
  )
  return items.data;
}

export default Items;