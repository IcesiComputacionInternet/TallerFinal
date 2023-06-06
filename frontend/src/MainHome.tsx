import React, { useState } from "react";
import Login from "./Login";
import Register from "./Register";

interface Props {
  handleLogin: () => void;
}

const MainHome = ({ handleLogin }: Props) => {
  const [isRegistering, setIsRegistering] = useState(false);

  const handleRegister = () => {
    setIsRegistering(true);
  };

  const handleRegistrationComplete = () => {
    setIsRegistering(false);
    handleLogin();
  };

  return (
    <div className="d-flex flex-column align-items-center justify-content-center" style={{ height: "100vh" }}>
      <h1 className="text-center mb-4" style={{ fontSize: "2rem" }}>Bienvenido a la página de inicio</h1>
      <div className="d-flex justify-content-center">
        {isRegistering ? (
          <Register onRegistrationComplete={handleRegistrationComplete} />
        ) : (
          <>
            <button className="btn btn-outline-primary" onClick={handleLogin}>
              Iniciar sesión
            </button>
            <button className="btn btn-outline-primary" onClick={handleRegister}>
              Registrarse
            </button>
          </>
        )}
      </div>
    </div>
  );
};

export default MainHome;