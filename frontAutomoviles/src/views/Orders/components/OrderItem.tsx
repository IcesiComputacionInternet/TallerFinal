interface Props {
    Order: Order
}

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

function OrderItem ({Order}: Props) {
    return (
        <div className="card text-bg-dark mb-3" style={{maxWidth: '750px'}}>
            <div className="row g-0">
                <div className="col-md-8">
                    <div className="card-body d-flex flex-column">
                        <div className="d-flex justify-content-between align-items-center mb-3">
                            <h5 className="card-title">Orden: {Order.purchaseOrderId}</h5>
                            <p className="card-text">Status: {Order.status}</p>
                            <p className="card-text">Total: {Order.total}</p>
                        </div>
                        <div className="d-flex flex-column">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default OrderItem;