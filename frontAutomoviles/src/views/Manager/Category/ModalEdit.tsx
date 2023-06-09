import { useState } from "react";
import { Modal } from "react-bootstrap";
import { updateCategory } from "../../../services/categories";

interface Props {
    Category: any,
    handleCancel: () => void,
    setEditModal: (value: boolean) => void
}

function ModalEdit({Category, handleCancel, setEditModal}: Props) {
    const [newName, setNewName] = useState(Category.name);
    const [newDescription, setNewDescription] = useState(Category.description);

    const handleChangeName = (event: any) => {
        setNewName(event.target.value);
    }

    const handleChangeDescription = (event: any) => {
        setNewDescription(event.target.value);
    }

    const handleSaveEdit = () => {
        updateCategory(Category.categoryId, newName, newDescription).then((result) => {
            console.log(result);
            setEditModal(false);
        }).catch((error) => {
            console.log(error);
        });  
    }

    return (
        <Modal
            show={true}
            onHide={() => {}}
            backdrop="static"
            keyboard={false}
        >
            <Modal.Header closeButton>
                <Modal.Title>Edit Category: {Category.name}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Category Name</label>
                        <input type="text" className="form-control" id="name" placeholder="Enter category name" onChange={handleChangeName}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Category Description</label>
                        <input type="text" className="form-control" id="description" placeholder="Enter category description" onChange={handleChangeDescription}/>
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