import { faUser,
        faHouse,
         faCartShopping } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Profile = () => {
    return (
        <li>
            <a href="/profile" className="nav-link text-white">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faUser}/>
                    </li>
                    <li className="list-inline-item">
                        Profile
                    </li>
                </ul>
            </a>
        </li>
    )
}

const Home = () => {
    return (
        <li>
            <a href="/home" className="nav-link text-white">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <FontAwesomeIcon icon={faHouse}/>
                    </li>
                    <li className="list-inline-item">
                        Home
                    </li>
                </ul>
            </a>
        </li>
    )
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



function Navbar() {
    return (
        <header>
            <div className="px-3 py-2 bg-dark text-white">
                <div className="container">
                    <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        <a href="/" className="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none">
                            eShop
                        </a>
                        <ul className="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                            <Profile />
                            <Home />
                            <Cart />
                        </ul>
                    </div>
                </div>
            </div>
        </header>
    )
}

export default Navbar;