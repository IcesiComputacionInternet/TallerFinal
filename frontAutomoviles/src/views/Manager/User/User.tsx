import {useEffect, useState} from 'react';
import { getUsersPage } from '../../../services/users';
import Pagination from '../../../components/Pagination';
import ModalChangeRole from './ModalChangeRole';

function User() {
    const [users, setUsers] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const [selectedUser, setSelectedUser] = useState({} as any);

    const [changeRoleModal, setChangeRoleModal] = useState(false);

    useEffect(() => {
        getUsersPage(currentPage).then((result) => {
            setTotalPages(result.totalPages);
            setUsers(result.items);
        });
    }, [currentPage]);

    const handlePageChange = (pageNumber: number) => {
        setCurrentPage(pageNumber);
    };

    const handleChangeRole = (user : any) => {
        setChangeRoleModal(true);
        setSelectedUser(user);
    }

    return (
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Users</h1>
            </div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Address</th>
                        <th scope="col">Role</th>
                        <th scope="col" className='d-flex justify-content-center'>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user: any) => (
                        <tr>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.address}</td>
                            <td>{user.role.roleName}</td>
                            <td className='d-flex justify-content-center'>
                                <button className="btn btn-primary mx-2" onClick={() => handleChangeRole(user)}>Change Role</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Pagination
                totalPages={totalPages}
                currentPage={currentPage}
                handlePageChange={handlePageChange}
            />
            {changeRoleModal && (
                <ModalChangeRole user={selectedUser} handleCancel={() => setChangeRoleModal(false)}/>
            )}
        </div>
    );
}

export default User;