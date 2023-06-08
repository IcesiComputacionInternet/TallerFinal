import Logout from "./Logout"

function AdminNavbar (){

    return (
      <>
        <nav className="navbar navbar-expand-lg bg-body-tertiary" style={{backgroundColor: "#212529"}}>
          <div className="container">
              <a className="navbar-brand" href="/admin/home">
                  <img src="data:image/svg+xml;charset=UTF-8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22white%22%20class%3D%22bi%20bi-bag-heart-fill%22%20viewBox%3D%220%200%2016%2016%22%3E%0A%20%20%3Cpath%20d%3D%22M11.5%204v-.5a3.5%203.5%200%201%200-7%200V4H1v10a2%202%200%200%200%202%202h10a2%202%200%200%200%202-2V4h-3.5ZM8%201a2.5%202.5%200%200%201%202.5%202.5V4h-5v-.5A2.5%202.5%200%200%201%208%201Zm0%206.993c1.664-1.711%205.825%201.283%200%205.132-5.825-3.85-1.664-6.843%200-5.132Z%22%2F%3E%0A%3C%2Fsvg%3E"  alt="Logo" width="25" height="25" className="d-inline-block align-text-top"/>
              </a>
              <div className="collapse navbar-collapse justify-content-center">
              <ul className="navbar-nav me-auto mb-2 mb-lg-0" style={{fontSize: "1.25rem"}}>
                  <li className="nav-item">
                  <a className="nav-link text-white" aria-current="page" href="/admin/users">Users</a>
                  </li>
                  <li className="nav-item">
                  <a className="nav-link text-white" aria-current="page" href="/admin/items">Items</a>
                  </li>
                  <li className="nav-item">
                  <a className="nav-link text-white" aria-current="page" href="/admin/orders">Orders</a>
                  </li>
              </ul>
              <Logout />
              </div>
          </div>
        </nav>
      </>
    );
        
  }
      
  export default AdminNavbar;