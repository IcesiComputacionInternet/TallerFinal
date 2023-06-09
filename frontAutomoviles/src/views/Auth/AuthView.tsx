import { useState } from "react";

import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";

interface Props {
    setLogin: () => void;
    setInfoToast: (message:string, title:string) => void;
}

function AuthView ({setLogin, setInfoToast}: Props) {
    const [viewLogin, setViewLogin] = useState<boolean>(true);

    const toggleView = () => {
        setViewLogin(!viewLogin);
    };

    return (
        <div className="container d-flex align-items-center">
            <div className="row justify-content-center w-100">
                {viewLogin ? (
                        <div className="row" style={{height:'80.5vh'}}>
                            <div className="col-md-6 container d-flex">
                                <LoginForm setLogin={setLogin} setInfoToast={setInfoToast}/>
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
                            <RegisterForm setViewLogin={toggleView}/>
                        </div>
                        <p className="text-center py-3">
                            ¿Ya tienes una cuenta?{' '}
                            <button className="btn btn-link" onClick={toggleView}>
                                Inicia sesión
                            </button>
                        </p>
                    </div>
                )}
            </div>
        </div>
    )
}

export default AuthView;