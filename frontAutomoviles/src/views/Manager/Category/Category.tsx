import { useEffect, useState } from 'react';
import { getCategoriesPage } from '../../../services/categories';
import Pagination from '../../../components/Pagination';
import ModalEdit from './ModalEdit';
import ModalCreate from './ModalCreate';

interface Props {
    setInfoToast: (message: string, title: string) => void;
}

function Category ({setInfoToast}: Props) {
    const [categories, setCategories] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    const [createModal, setCreateModal] = useState(false);
    const [editModal, setEditModal] = useState(false);
    const [category, setCategory] = useState({});

    useEffect(() => {
        getCategoriesPage(currentPage).then((result) => {
            setTotalPages(result.totalPages);
            setCategories(result.items);
        });
    }, [currentPage]);

    const handlePageChange = (pageNumber: number) => {
        setCurrentPage(pageNumber);
    };

    const handleEdit = (category: any) => {
        setEditModal(true);
        setCategory(category);
    }

    const handleCancelEdit = () => {
        setEditModal(false);
    }
    const handleCancelCreate = () => {
        setCreateModal(false);
    }

    return (
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Categories</h1>
            </div>
            <button className="btn btn-primary my-1" onClick={() => setCreateModal(true)}>Create</button>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Category</th>
                        <th scope="col">Description</th>
                        <th scope="col" className='d-flex justify-content-center'>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {categories.map((category: any) => (
                        <tr key={category.categoryId}>
                            <td>{category.name}</td>
                            <td>{category.description}</td>
                            <td className='d-flex justify-content-center'>
                                <button className="btn btn-primary" onClick={() => handleEdit(category)}>Edit</button>
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
            {editModal && (
                <ModalEdit Category={category} handleCancel={handleCancelEdit} setEditModal={setEditModal} setInfoToast={setInfoToast}/>
            )}
            {createModal && (
                <ModalCreate handleCancel={handleCancelCreate} setCreateModal={setCreateModal} setInfoToast={setInfoToast}/>
            )}
        </div>
    )   
}

export default Category;