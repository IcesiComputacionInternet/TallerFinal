import {faTag,
        faScroll,
        faBagShopping,
        faUsers} from "@fortawesome/free-solid-svg-icons";
import {faProductHunt} from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const ManageCategories = () => {
    return(
        <strong>
            <a href="/manage/categories" className="nav-link text-black">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faTag} size="2x"/>
                    </li>
                    <li className="list-inline-item" style={{fontSize:'20px', borderBottom:'1px solid #000'}}>
                        Manage Categories
                    </li>
                </ul>
            </a>
        </strong>
    )
}

const ManageRoles = () => {
    return(
        <strong>
            <a href="/manage/roles" className="nav-link text-black">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faScroll} size="2x"/>
                    </li>
                    <li className="list-inline-item" style={{fontSize:'20px', borderBottom:'1px solid #000'}}>
                        Manage Roles
                    </li>
                </ul>
            </a>
        </strong>
    )
}

const ManageItems = () => {
    return(
        <strong>
            <a href="/manage/items" className="nav-link text-black">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faProductHunt} size="2x"/>
                    </li>
                    <li className="list-inline-item" style={{fontSize:'20px', borderBottom:'1px solid #000'}}>
                        Manage Items
                    </li>
                </ul>
            </a>
        </strong>
    )
}

const ManageOrders = () => {
    return(
        <strong>
            <a href="/manage/orders" className="nav-link text-black">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faBagShopping} size="2x"/>
                    </li>
                    <li className="list-inline-item" style={{fontSize:'20px', borderBottom:'1px solid #000'}}>
                        Manage Orders
                    </li>
                </ul>
            </a>
        </strong>
    )
}

const ManageUsers = () => {
    return(
        <strong>
            <a href="/manage/users" className="nav-link text-black">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faUsers} size="2x"/>
                    </li>
                    <li className="list-inline-item" style={{fontSize:'20px', borderBottom:'1px solid #000'}}>
                        Manage Users
                    </li>
                </ul>
            </a>
        </strong>
    )
}


function Manager() {
    return(
        <div className="container">
            <div className="d-flex flex-column align-items-center">
                <h1 className="text-center">Manager</h1>
            </div>
            <div className="row d-flex">
                {localStorage.getItem('role') === 'ADMIN' ? <>
                    <ManageCategories/>
                    <ManageRoles/>
                    <ManageItems/>
                    <ManageOrders/>
                    <ManageUsers/>
                </>: <>
                    <ManageItems/>
                    <ManageOrders/>
                </>}
            </div>
        </div>
    )
}

export default Manager;