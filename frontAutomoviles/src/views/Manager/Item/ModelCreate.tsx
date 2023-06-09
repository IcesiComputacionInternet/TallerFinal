import { useEffect, useState } from "react";
import { Modal } from "react-bootstrap";
import { postItem } from "../../../services/items";

interface Props {
    handleCancel: () => void;
    setInfoToast: (message: string, title: string) => void;
}

function ModalCreate({ handleCancel, setInfoToast }: Props) {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState(0);
    const [category, setCategory] = useState(0);
    const [categories, setCategories] = useState([]);
    const [imageUrl, setImageUrl] = useState("https://via.placeholder.com/300x300");

    useEffect(() => {

    }, []);

    const handleCreate = () => {
        postItem(name, description, price, "", imageUrl).then((result) => {
            if (result) {
                setInfoToast("Item created successfully", "Success");
                window.location.reload();
            }
        }).catch((error) => {
            setInfoToast(error.message, "Error");
        });
    }

    return (
        <Modal
            show={true}
            onHide={() => {}}
            backdrop="static"
            keyboard={false}
        >
            <Modal.Header>
                <Modal.Title>Create Item</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Item Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            placeholder="Enter item name"
                            value={name}
                            onChange={(event) => setName(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Item Description</label>
                        <input
                            type="text"
                            className="form-control"
                            id="description"
                            placeholder="Enter item description"
                            value={description}
                            onChange={(event) => setDescription(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Item Price</label>
                        <input
                            type="number"
                            className="form-control"
                            id="price"
                            placeholder="Enter item price"
                            value={price}
                            onChange={(event) => setPrice(parseFloat(event.target.value))}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="category">Item Category</label>
                        <select 
                            className="form-control" 
                            id="category" 
                            value={category}
                            onChange={(event) => setCategory(parseInt(event.target.value))}
                        >
                            <option value="1">Category 1</option>
                            <option value="2">Category 2</option>
                            <option value="3">Category 3</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="image">Item Image</label>
                        <input
                            type="text"
                            className="form-control"
                            id="image"
                            placeholder="Enter item image url"
                            value={imageUrl}
                            onChange={(event) => setImageUrl(event.target.value)}
                        />
                    </div>
                </form>

                <div className="card text-bg-dark mb-3" style={{maxWidth: '750px'}}>
                    <div className="row g-0">
                        <div className="col-md-4 d-flex justify-content-center align-items-center">
                            <img 
                                src={imageUrl} 
                                className="card-img-top" 
                                alt={name} 
                            />
                        </div>
                        <div className="col-md-8">
                            <div className="card-body d-flex flex-column">
                                <div className="d-flex justify-content-between align-items-center mb-3">
                                    <h5 className="card-title">{name}</h5>
                                </div>
                                <p className="card-text">{description}</p>
                                <p className="card-text">Price: {price}</p>
                                <p className="card-text">Category: {categories[category]}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <button
                    className="btn btn-primary"
                    onClick={handleCreate}
                >
                    Save
                </button>
                <button
                    className="btn btn-secondary"
                    onClick={handleCancel}
                >
                    Cancel
                </button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalCreate;