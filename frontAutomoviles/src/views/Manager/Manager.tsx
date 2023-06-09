import {faTag,
        faScroll } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const ManageCategories = () => {
    return(
        <strong>
            <a href="/manage/categories" className="nav-link text-black">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faTag}/>
                    </li>
                    <li className="list-inline-item">
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
                        <FontAwesomeIcon icon={faScroll}/>
                    </li>
                    <li className="list-inline-item">
                        Manage Roles
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
                <ManageCategories/>
                <ManageRoles/>
            </div>
        </div>
    )
}

export default Manager;