import { useEffect, useState } from "react";
import { Modal } from "react-bootstrap";
import { patchItem } from "../../../services/items";
import { getCategoriesList } from "../../../services/categories";

interface Props {
    item: any,
    handleCancel: () => void;
    setInfoToast: (message: string, title: string) => void;
}

interface Category {
    categoryId: string;
    name: string;
}

function ModalEdit({item, handleCancel, setInfoToast }: Props) {
    const [name, setName] = useState(item.name);
    const [description, setDescription] = useState(item.description);
    const [price, setPrice] = useState(item.price);
    const [category, setCategory] = useState(item.category.categoryId);
    const [categories, setCategories] = useState<Category[]>([]);
    const [imageUrl, setImageUrl] = useState(item.imageUrl);

    useEffect(() => {
        getCategoriesList().then((result) => {
            setCategories(result);
        });
    }, []);

    const handleEdit = () => {
        patchItem(item.itemId, name, description, price, imageUrl, category).then((result) => {
            if (result) {
                setInfoToast("Item edited successfully", "Success");
                window.location.reload();
            }
        }).catch((error) => {
            setInfoToast(error.message, "Error");
        });
    }

    return (
        <Modal
            show={true}
            onHide={() => { }}
            backdrop="static"
            keyboard={false}
        >
            <Modal.Header>
                <Modal.Title>Edit Item</Modal.Title>
            </Modal.Header>
            <Modal.Body
            >
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
                            onChange={(event) => setPrice(Number(event.target.value))}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="category">Item Category</label>
                        <select 
                            className="form-control" 
                            id="category" 
                            value={category}
                            onChange={(event) => setCategory(event.target.value)}
                        >
                            {categories.map((ct) => (
                                <option key={ct.categoryId} value={ct.categoryId}>{ct.name}</option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="imageUrl">Item Image</label>
                        <input
                            type="text"
                            className="form-control"
                            id="imageUrl"
                            placeholder="Enter item image"
                            value={imageUrl}
                            onChange={(event) => setImageUrl(event.target.value)}
                        />
                    </div>
                </form>
                <div className="card text-bg-dark mb-3 my-2" style={{maxWidth: '750px'}}>
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
                                <p className="card-text">Category: {categories.filter((ct) => ct.categoryId === category)[0]?.name}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <button className="btn btn-primary" onClick={handleCancel}>Cancel</button>
                <button className="btn btn-primary" onClick={handleEdit}>Save</button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalEdit;