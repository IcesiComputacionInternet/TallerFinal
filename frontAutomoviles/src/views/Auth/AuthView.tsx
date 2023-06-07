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
                <div className="row justify-content-center w-100" style={{height:'78vh'}}>
                    {viewLogin ? (
                        <>
                            <div className="col-md-6 container d-flex align-items-center">
                                <LoginForm setLogin={setLogin} />
                            </div>
                            <p className="text-center">
                                ¿No tienes una cuenta?{' '}
                                <button className="btn btn-link" onClick={toggleView}>
                                    Regístrate
                                </button>
                            </p>
                        </>
                    ) : (
                        <>
                            <div className="col-md-6 container d-flex align-items-center">
                                <RegisterForm />
                            </div>
                            <p className="text-center mt-3">
                                ¿Ya tienes una cuenta?{' '}
                                <button className="btn btn-link" onClick={toggleView}>
                                    Inicia sesión
                                </button>
                            </p>
                        </>
                    )}
                </div>
            </div>
        </div>
    )
}

export default AuthView;