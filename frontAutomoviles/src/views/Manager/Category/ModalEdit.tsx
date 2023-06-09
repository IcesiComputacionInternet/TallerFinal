import { useState } from "react";
import { Modal } from "react-bootstrap";
import { updateCategory } from "../../../services/categories";

interface Props {
    Category: any;
    handleCancel: () => void;
    setEditModal: (value: boolean) => void;
    setInfoToast: (message: string, title: string) => void;
}

function ModalEdit({Category, handleCancel, setEditModal, setInfoToast}: Props) {
    const [newName, setNewName] = useState(Category.name);
    const [newDescription, setNewDescription] = useState(Category.description);

    const handleChangeName = (event: any) => {
        setNewName(event.target.value);
    }

    const handleChangeDescription = (event: any) => {
        setNewDescription(event.target.value);
    }

    const handleSaveEdit = () => {
        updateCategory(Category.categoryId, newName, newDescription).then(() => {
            setEditModal(false);
            setInfoToast("Category has been updated successfully!", "Success");
            window.location.reload();
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
                <Modal.Title>Edit Category: {Category.name}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Category Name</label>
                        <input 
                            type="text" 
                            className="form-control" 
                            id="name" 
                            placeholder="Enter category name" 
                            value={newName}
                            onChange={handleChangeName}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Category Description</label>
                        <input 
                            type="text" 
                            className="form-control" 
                            id="description" 
                            placeholder="Enter category description"
                            value={newDescription} 
                            onChange={handleChangeDescription}/>
                    </div>
                </form>
            </Modal.Body>
            <Modal.Footer>
                <button className="btn btn-primary" onClick={handleSaveEdit}>Save</button>
                <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
            </Modal.Footer>
        </Modal>
    )
}

export default ModalEdit;