import { useEffect, useState } from 'react';
import Pagination from '../../../components/Pagination';
import { getItemsPage, deleteItem } from '../../../services/items';
import ModalView from './ModalView';
import ModalCreate from './ModalCreate';
import ModalEdit from './ModalEdit';

interface Props {
    setInfoToast: (message: string, title: string) => void;
}

function Item({setInfoToast}: Props) {
    const [items, setItems] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const [selectedItem, setSelectedItem] = useState({} as any);
    const [filter, setFilter] = useState("");

    const [viewModal, setViewModal] = useState(false);
    const [createModal, setCreateModal] = useState(false);
    const [editModal, setEditModal] = useState(false);

    useEffect(() => {
        getItemsPage(currentPage, filter).then((result) => {
            console.log(result.items);
            setTotalPages(result.totalPages);
            setItems(result.items);
        });
    }, [currentPage, filter]);

    const handlePageChange = (pageNumber: number) => {
        setCurrentPage(pageNumber);
    };

    const handleFilterChange = (event: any) => {
        setFilter(event.target.value);
    }

    const handleViewItem = (item:any) => {
        setSelectedItem(item);
        setViewModal(true);
    }

    const handleEditItem = (item:any) => {
        setSelectedItem(item);
        setEditModal(true);
    }

    const handleDeleteItem = (itemId:string) => {
        deleteItem(itemId).then(() => {
            setInfoToast("Item deleted successfully", "Success");
            window.location.reload();
        }).catch((error) => {
            setInfoToast(error.message, "Error");
        });
    }

    return (
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Items</h1>
            </div>
            <button className="btn d-flex btn-primary my-1" onClick={() => setCreateModal(true)}>Create</button>
            <input
                type="text"
                className="form-control w-50 my-1"
                placeholder="Search"
                value={filter}
                onChange={handleFilterChange}
            />
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col" >Item</th>
                        <th scope="col" >Description</th>
                        <th scope='col' >Price</th>
                        <th scope='col' >Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item: any) => (
                        <tr key={item.itemId} >
                            <td>{item.name}</td>
                            <td>{item.description}</td>
                            <td>{item.price}</td>
                            <td>
                                <button className="btn btn-primary mx-2" onClick={() => handleViewItem(item)}>View</button>
                                <button className="btn btn-primary mx-2" onClick={() => handleEditItem(item)}>Edit</button>
                                <button className="btn btn-danger mx-2" onClick={() => handleDeleteItem(item.itemId)}>Delete</button>
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
            {viewModal && (
                <ModalView item={selectedItem} handleCancel={() => setViewModal(false)}/>
            )}
            {createModal && (
                <ModalCreate setInfoToast={setInfoToast} handleCancel={() => setCreateModal(false)}/>
            )}
            {editModal && (
                <ModalEdit item={selectedItem} setInfoToast={setInfoToast} handleCancel={() => setEditModal(false)}/>
            )}
        </div>
    );
}

export default Item;