function PurchaseOrder() {
    return (
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Purchase Orders</h1>
            </div>
            <button className="btn btn-primary my-1">Create</button>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Client</th>
                        <th scope="col">Price</th>
                        <th scope="col">Status</th>
                        <th scope="col" className='d-flex justify-content-center'>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>John Doe</td>
                        <td>$100</td>
                        <td>Completed</td>
                        <td className='d-flex justify-content-center'>
                            <button className="btn btn-primary">Edit</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default PurchaseOrder;