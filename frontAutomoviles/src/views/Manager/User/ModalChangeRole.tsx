import { useEffect } from "react";
import {Modal} from "react-bootstrap"

interface Props {
    user: any;
    handleCancel: () => void;
}

function ModalChangeRole({user, handleCancel}: Props) {



    return (
        <Modal
            show={true}
            onHide={() => {}}
            backdrop="static"
            keyboard={false}
        >
            <Modal.Header>
                <Modal.Title>Change Role</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form>
                    <div className="form-group">
                        <label htmlFor="firstName">First Name</label>
                        <input type="text" className="form-control" id="firstName" value={user.firstName} readOnly/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="lastName">Last Name</label>
                        <input type="text" className="form-control" id="lastName" value={user.lastName} readOnly/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="role">Role</label>
                        <select className="form-control" id="role">
                            <option>ADMIN</option>
                            <option>SHOP</option>
                            <option>USER</option>
                        </select>
                    </div>
                </form>
            </Modal.Body>
            <Modal.Footer>
                <button className="btn btn-primary">Save</button>
                <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalChangeRole;