import { useEffect, useState } from 'react';
import { getOrders, updateStatus } from '../../../services/orders';
import Pagination from '../../../components/Pagination';

function PurchaseOrder() {
    const [orders, setOrders] = useState([])
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        getOrders(currentPage).then((result) => {
            setTotalPages(result.totalPages);
            setOrders(result.items);
        });
    }, [currentPage]);
    
    const handleUpdate = (orderId: string, status: string) => {
        if(status === "Processing_order"){
            updateStatus(orderId, "Sending_order").then(() => {
                window.location.reload();
            }).catch((error) => {
                console.log(error);
            });
        }
    }

    const handlePageChange = (pageNumber: number) => {
        setCurrentPage(pageNumber);
    };

    return (
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Purchase Orders</h1>
            </div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Price</th>
                        <th scope="col">Status</th>
                        <th scope="col" className='d-flex justify-content-center'>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {orders.map((order: any) => (
                        <tr>
                            <td>{order.purchaseOrderId}</td>
                            <td>{order.total}</td>
                            <td>{order.status}</td>
                            <td className='d-flex justify-content-center'>
                                <button className="btn btn-primary mx-2" onClick={() => handleUpdate(order.purchaseOrderId, order.status)}>Update Status</button>
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
        </div>
    );
}

export default PurchaseOrder;