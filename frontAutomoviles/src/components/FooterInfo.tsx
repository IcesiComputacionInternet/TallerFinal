import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGithub } from "@fortawesome/free-brands-svg-icons";

function FooterInfo() {
    return (
        <footer className="p-3 bg-dark text-white" style={{ height: '10vh' }}>
            <div className="container d-flex flex-wrap align-items-center justify-content-center">
                <ul className="list-inline">
                    <li className="list-inline-item">
                        <a href="https://github.com/GilmarAmezquita" target="_blank" rel="noopener noreferrer" className="text-decoration-none text-white">
                            <ul className="list-inline">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faGithub} />
                                </li>
                                <li className="list-inline-item">
                                    Gilmar Amezquita
                                </li>
                            </ul>
                        </a>
                    </li>
                    <li className="list-inline-item">
                        <a href="https://github.com/Juan-dev123" target="_blank" rel="noopener noreferrer" className="text-decoration-none text-white">
                            <ul className="list-inline">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faGithub} />
                                </li>
                                <li className="list-inline-item">
                                    Juan Pablo Ramos
                                </li>
                            </ul>
                        </a>
                    </li>
                    <li className="list-inline-item">
                        <a href="https://github.com/Rockthor1106" target="_blank" rel="noopener noreferrer" className="text-decoration-none text-white">
                            <ul className="list-inline">
                                <li className="list-inline-item">
                                    <FontAwesomeIcon icon={faGithub} />
                                </li>
                                <li className="list-inline-item">
                                    Jhan Carvajal
                                </li>
                            </ul>
                        </a>
                    </li>
                </ul>
            </div>
        </footer>
    );
}

export default FooterInfo;