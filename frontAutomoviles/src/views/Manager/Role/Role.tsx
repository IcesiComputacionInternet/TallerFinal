import { useEffect, useState } from 'react';
import Pagination from '../../../components/Pagination';
import { getRolesPage } from '../../../services/roles';
import ModalCreate from './ModalCreate';

interface Props {
    setInfoToast: (message: string, title: string) => void;
}

function Role ({setInfoToast}: Props) {
    const [roles, setRoles] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    const [createModal, setCreateModal] = useState(false);

    useEffect(() => {
        getRolesPage(currentPage).then((result) => {
            setTotalPages(result.totalPages);
            setRoles(result.items);
        });
    }, [currentPage]);

    const handlePageChange = (pageNumber: number) => {
        setCurrentPage(pageNumber);
    };

    const handleCancelCreate = () => {
        setCreateModal(false);
    }

    return (
        <div className="container">
            <button className="btn btn-primary my-4" onClick={() => setCreateModal(true)}>Create</button>
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Roles</h1>
            </div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col" >Role</th>
                        <th scope="col" >Description</th>
                    </tr>
                </thead>
                <tbody>
                    {roles.map((role: any) => (
                        <tr key={role.roleId} >
                            <td>{role.roleName}</td>
                            <td>{role.description}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Pagination
                totalPages={totalPages}
                currentPage={currentPage}
                handlePageChange={handlePageChange}
            />
            {createModal && (
                <ModalCreate handleCancel={handleCancelCreate} setCreateModal={setCreateModal} setInfoToast={setInfoToast}/>
            )}
        </div>
    )
}

export default Role;