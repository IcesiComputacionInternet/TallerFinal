import { Modal } from "react-bootstrap";

interface Props {
    item: any;
    handleCancel: () => void;
}

function ModalView({item, handleCancel}: Props) {
    return (
        <Modal
            show={true}
            onHide={() => {}}
            backdrop="static"
            keyboard={false}
        >
            <Modal.Header>
                <Modal.Title>Item: {item.name}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="card text-bg-dark mb-3" style={{maxWidth: '750px'}}>
                    <div className="row g-0">
                        <div className="col-md-4 d-flex justify-content-center align-items-center">
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
                </div>
            </Modal.Body>
            <Modal.Footer>
                <button className="btn btn-primary" onClick={handleCancel}>Close</button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalView;