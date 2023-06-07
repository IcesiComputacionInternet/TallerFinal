import { Button, Modal } from "react-bootstrap";

interface PurchasesProps {
  show: boolean;
  onHide: () => void;
  addItemToOrders: () => void;
}

const Purchases = ({ show, onHide, addItemToOrders }: PurchasesProps) => {
  const handleAddToOrders = () => {
    addItemToOrders();
    onHide();
  };

  return (
    <Modal show={show} onHide={onHide}>
      <Modal.Header closeButton>
        <Modal.Title>Confirmar Compra</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <p>¿Estás seguro de que deseas agregar este producto a tu lista de compras?</p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={handleAddToOrders}>
          Comprar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default Purchases;