interface Order {
  orders: [
    {
      id: number;
      user: string;
      status: string;
    }
  ];
}

const OrdersTable = ({ orders }: Order) => {
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title">All Orders</h3>
              <table className="table">
                <thead>
                  <tr>
                    <th scope="col">Id</th>
                    <th scope="col">User</th>
                    <th scope="col">Status</th>
                    <th scope="col" colSpan={3}>
                      Status
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {orders.map((order) => (
                    <tr key={order.id}>
                      <td>{order.id}</td>
                      <td>{order.user}</td>
                      <td>{order.status}</td>
                      <td>
                        <button
                          className="btn btn-outline-primary"
                          onClick={() => {}}
                        >
                          Pendiente
                        </button>
                      </td>
                      <td>
                        <button
                          className="btn btn-outline-primary"
                          onClick={() => {}}
                        >
                          En curso
                        </button>
                      </td>
                      <td>
                        <button
                          className="btn btn-outline-primary"
                          onClick={() => {}}
                        >
                          Terminada
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrdersTable;
