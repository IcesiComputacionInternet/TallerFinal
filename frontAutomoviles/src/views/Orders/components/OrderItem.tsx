import { useState } from 'react';

import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';


interface Props {
    Order: Order
}

interface Order {
    purchaseOrderId: string,
    status: string,
    total: number,
    items: Item[]
}

interface Item {
    item: {
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
}

function OrderItem ({Order}: Props) {
    const [dialogVisible, setDialogVisible] = useState(false);
    const [selectedOrder, setSelectedOrder] = useState<Order | null>(null);

    const openDialog = () => {
        setSelectedOrder(Order);
        setDialogVisible(true);
    };
    
    const closeDialog = () => {
        setSelectedOrder(null);
        setDialogVisible(false);
    };

    return (
        <div className='card text-bg-dark mb-3' style={{maxWidth: '700px'}}>
            <div className='card-body text-center'>
                <h2 className='card-title'>Order: {Order.purchaseOrderId}</h2>
                <ul className="row list-inline align-items-center justify-content-center">
                    <li className="list-inline-item">
                        <p className="card-text">Status: {Order.status}</p>
                    </li>
                    <li className="list-inline-item">
                        <p className="card-text">Total Price: ${Order.total}</p>
                    </li>
                </ul> 
                <button className="btn btn-primary" onClick={openDialog}>View Order</button>
            </div>
            {dialogVisible && selectedOrder && (
                <Modal 
                    show={dialogVisible}
                    size='lg'
                    aria-labelledby='contained-modal-title-vcenter'
                    centered
                >
                    <Modal.Header closeButton>
                        <Modal.Title id='contained-modal-title-vcenter'>
                            Order: {selectedOrder.purchaseOrderId}
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <h4>Status: {selectedOrder.status}</h4>
                        <p>Total Price: ${selectedOrder.total}</p>
                        <ul className="list-group">
                            {selectedOrder.items.map((item, index) => (
                                <li className="list-group-item my-2" key={index}>
                                    <div className="row">
                                        <div className="col-md-4">
                                            <img 
                                                src={item.imageUrl} 
                                                className="card-img-top" 
                                                alt={item.name} 
                                            />
                                        </div>
                                        <div className="col-md-8">
                                            <div className="card-body d-flex flex-column">
                                                <div className="d-flex justify-content-between align-items-center mb-3">
                                                    <h5 className="card-title">{item.name}</h5>
                                                </div>
                                                <p className="card-text">{item.description}</p>
                                                <p className="card-text">Price: {item.price}</p>
                                                <p className="card-text">Category: {item.category.name}</p>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={closeDialog}>Close</Button>
                    </Modal.Footer>
                </Modal>
            )}
        </div>
    )
}

export default OrderItem;