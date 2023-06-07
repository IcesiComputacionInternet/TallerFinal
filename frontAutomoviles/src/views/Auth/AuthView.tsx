import { useState } from "react";
import { faCarSide} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";

interface Props {
    setLogin: () => void;
}

function AuthView ({setLogin}: Props) {
    const [viewLogin, setViewLogin] = useState<boolean>(true);

    const toggleView = () => {
        setViewLogin(!viewLogin);
    };

    return (
        <div>
            <header className="p-3 bg-dark text-white" style={{height:'12vh'}}>
                <div className="container d-flex align-items-center justify-content-center">
                    <span className="text-center display-6">
                        <FontAwesomeIcon icon={faCarSide} className="me-2" />
                        <strong>eShop Automoviles</strong>
                    </span>
                </div>
            </header>
            <div className="container d-flex align-items-center">
                <div className="row justify-content-center w-100">
                    {viewLogin ? (
                            <div className="row" style={{height:'78vh'}}>
                                <div className="col-md-6 container d-flex">
                                    <LoginForm setLogin={setLogin} />
                                </div>
                                <p className="text-center">
                                    ¿No tienes una cuenta?{' '}
                                    <button className="btn btn-link" onClick={toggleView}>
                                        Regístrate
                                    </button>
                                </p>
                            </div>
                    ) : (
                        <div className="py-4">
                            <div className="col-md-6 container d-flex">
                                <RegisterForm />
                            </div>
                            <p className="text-center">
                                ¿Ya tienes una cuenta?{' '}
                                <button className="btn btn-link" onClick={toggleView}>
                                    Inicia sesión
                                </button>
                            </p>
                        </div>
                    )}
                </div>
            </div>
        </div>
    )
}

export default AuthView;