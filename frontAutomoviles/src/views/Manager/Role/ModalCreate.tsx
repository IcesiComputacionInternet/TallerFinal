import {useState} from "react";
import Modal from "react-bootstrap/Modal";
import { postRole } from "../../../services/roles";

interface Props {
    handleCancel: () => void,
    setCreateModal: (value: boolean) => void;
    setInfoToast: (message: string, title: string) => void;
}

function ModalCreate({handleCancel, setCreateModal, setInfoToast}: Props) {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    const handleChangeName = (event: any) => {
        setName(event.target.value);
    }

    const handleChangeDescription = (event: any) => {
        setDescription(event.target.value);
    }

    const handleSaveRole = () => {
        postRole(name, description).then(() => {
            setCreateModal(false);
            setInfoToast("Role created successfully", "Success");
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
                <Modal.Title>Create Role</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Role Name</label>
                        <input type="text" className="form-control" id="name" placeholder="Enter role name" onChange={handleChangeName}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Role Description</label>
                        <input type="text" className="form-control" id="description" placeholder="Enter role description" onChange={handleChangeDescription}/>
                    </div>
                </form>
            </Modal.Body>
            <Modal.Footer>
                <button className="btn btn-primary" onClick={handleSaveRole}>Save</button>
                <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
            </Modal.Footer>
        </Modal>
    )
}

export default ModalCreate;