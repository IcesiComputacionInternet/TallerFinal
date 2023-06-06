import { NavigateFunction, useNavigate } from "react-router-dom";

function MainHome() {

  const navigation : NavigateFunction = useNavigate();

  const handleLogin = async (event: any) => {

    event.preventDefault();
    
    navigation("/login");

};

  const handleRegister = async (event: any)=> {
    event.preventDefault();
    
    navigation("/register");

  };

  return (
    <div className="d-flex flex-column align-items-center justify-content-center" style={{ height: "100vh" }}>
      <h1 className="text-center mb-4" style={{ fontSize: "2rem" }}>Bienvenido a la página de inicio</h1>
      <div className="d-flex justify-content-center">
        <button className="btn btn-outline-primary" onClick={handleLogin}>
          Iniciar sesión
        </button>
        <button className="btn btn-outline-primary" onClick={handleRegister}>
          Registrarse
        </button>
      </div>
    </div>
  );
}

export default MainHome;