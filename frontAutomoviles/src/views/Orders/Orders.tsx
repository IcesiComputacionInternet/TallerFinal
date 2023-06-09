import { useEffect, useState } from 'react';
import OrderItem from './components/OrderItem';
import { getOrders } from '../../services/orders';
import Pagination from '../../components/Pagination';

interface Order {
    purchaseOrderId: string,
    status: string,
    total: number,
    items: Item[]
}

interface Item {
    item: {
        itemId: string;
        name: string;
        description: string;
        price: number;
        imageUrl: string;
        category: {
            name: string;
            description: string;
        }
    }
}

function Orders () {
    const [orders, setOrders] = useState<Order[]>([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        getOrders(currentPage).then((result) => {
            setTotalPages(result.totalPages);
            setOrders(result.items);
        });
    }, [currentPage]);


    const handlePageChange = (pageNumber: number) => {
        setCurrentPage(pageNumber);
    };

    return (
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Orders</h1>
                {orders.map((order: any) => (
                    <div key={order.purchaseOrderId}>
                        <OrderItem Order={order}/>
                    </div>
                ))}
            </div>  
        <Pagination 
            totalPages={totalPages} 
            currentPage={currentPage}
            handlePageChange={handlePageChange}
        />
        </div>
    )
}

export default Orders