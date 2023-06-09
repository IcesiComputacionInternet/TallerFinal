import {useState} from "react";
import Modal from "react-bootstrap/Modal";
import { postCategory } from "../../../services/categories";

interface Props {
    handleCancel: () => void,
    setCreateModal: (value: boolean) => void
}

function ModalCreate({handleCancel, setCreateModal}: Props) {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    const handleChangeName = (event: any) => {
        setName(event.target.value);
    }

    const handleChangeDescription = (event: any) => {
        setDescription(event.target.value);
    }

    const handleSaveCategory = () => {
        postCategory(name, description).then((result) => {
            console.log(result);
            setCreateModal(false);
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
                <Modal.Title>Create Category</Modal.Title>
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
                <button className="btn btn-primary" onClick={handleSaveCategory}>Save</button>
                <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
            </Modal.Footer>
        </Modal>
    )
}

export default ModalCreate;