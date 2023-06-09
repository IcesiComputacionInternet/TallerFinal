import { useEffect, useState } from "react";
import {Modal} from "react-bootstrap"
import { getRolesList } from "../../../services/roles";
import { assignRole } from "../../../services/users";

interface Props {
    user: any;
    handleCancel: () => void;
}

function ModalChangeRole({user, handleCancel}: Props) {
    const [roles, setRoles] = useState([]);
    const [selectedRole, setSelectedRole] = useState(user.role.roleName);

    useEffect(() => {
        getRolesList().then((result) => {
            setRoles(result);
        });
    }, []);

    const handleSave = () => {
        assignRole(user.eshopUserId, selectedRole).then(() => {
            window.location.reload();
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
                <Modal.Title>Change Role</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <form>
                    <div className="form-group">
                        <label htmlFor="email">User ID</label>
                        <input type="text" className="form-control" id="userId" value={user.eshopUserId} readOnly/>
                    </div>
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
                        <select className="form-control" id="role" value={selectedRole} onChange={(event) => setSelectedRole(event.target.value)}>
                            {roles.map((role: any) => (
                                <option value={role.roleName}>{role.roleName}</option>
                            ))}
                        </select>
                    </div>
                </form>
            </Modal.Body>
            <Modal.Footer>
                <button className="btn btn-primary" onClick={handleSave}>Save</button>
                <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalChangeRole;