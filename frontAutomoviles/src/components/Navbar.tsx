import {faHouse,
        faCartShopping,
        faBagShopping,
        faRightFromBracket,
        faRightToBracket } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const logOut = () => {
    localStorage.clear();
}

const Cart = () => {
    return (
        <li>
            <a href="/cart" className="nav-link text-white">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faCartShopping}/>
                    </li>
                    <li className="list-inline-item">
                        Cart
                    </li>
                </ul>
            </a>
        </li>
    )
}

const Orders = () => {
    return (
        <li>
            <a href="/orders" className="nav-link text-white">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faBagShopping}/>
                    </li>
                    <li className="list-inline-item">
                        Orders
                    </li>
                </ul>
            </a>
        </li>
    )
}

const AuthView = () => {
    return (
        <li>
            <a href="/login" className="nav-link text-white">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faRightToBracket}/>
                    </li>
                    <li className="list-inline-item">
                        Log In
                    </li>
                </ul>
            </a>
        </li>
    )
}

const LogOut = () => {
    return(
        <li onClick={logOut}>
            <a href="/" className="nav-link text-white">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faRightFromBracket}/>
                    </li>
                    <li className="list-inline-item">
                        Log Out
                    </li>
                </ul>
            </a>
        </li>
    )
}

interface Props {
    isLoggedIn: boolean;
}

function Navbar({isLoggedIn}: Props) {
    const menuItems = []

    if(!isLoggedIn){
        menuItems.push(<AuthView />)
    }else if(localStorage.getItem('role') === 'USER'){
        menuItems.push(<Orders />)
        menuItems.push(<Cart />)
        menuItems.push(<LogOut />)
    }
    
    return (
        <header>
            <div className="px-3 py-2 bg-dark text-white">
                <div className="container">
                    <div className="d-flex justify-content-lg-start">
                        <a href="/" className="me-lg-auto text-white">
                            <ul className="list-inline d-flex align-items-center">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faHouse} size="xl"/>
                                </li>
                                <li className="list-inline-item" style={{fontSize:'20px'}}>
                                    eShop
                                </li>
                            </ul>
                        </a>
                        <ul className="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                            {menuItems}
                        </ul>
                    </div>
                </div>
            </div>
        </header>
    )
}

export default Navbar;