import { useEffect, useState } from 'react';
import OrderItem from './components/OrderItem';
import { getOrders } from '../../services/orders';

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

    useEffect(() => {
        getOrders().then((result) => {
            console.log(result.items);
            setOrders(result.items);
        });
    }, []);

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
        </div>
    )
}

export default Orders