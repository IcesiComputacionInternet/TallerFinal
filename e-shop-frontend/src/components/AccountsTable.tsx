const AccountsTable = ({ accounts }: any) => {
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h3 className="card-title">My Accounts</h3>
              <table className="table">
                <thead>
                  <tr>
                    <th scope="col">Account Number</th>
                    <th scope="col">Balance</th>
                  </tr>
                </thead>
                <tbody>
                  {accounts.map((account: any) => (
                    <tr key={account.accountId}>
                      <td>{account.accountNumber}</td>
                      <td>{account.balance}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <div className="d-grid gap-2">
                <button
                  className="btn btn-primary"
                  type="button"
                  disabled
                  onClick={() => {
                    window.location.href = "/create";
                  }}
                >
                  Create Account
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AccountsTable;
