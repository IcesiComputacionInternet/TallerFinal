import { NavigateFunction, useNavigate } from "react-router-dom";

function NotFound() {
    const navigation: NavigateFunction = useNavigate();
  
    const goHome = () => {
        navigation("/home");
    };        

  return (
    <div className="container d-flex align-items-center" style={{ height: "90vh" }}>
      <div className="row justify-content-center w-100">
        <div className="col-md-6 text-center">
          <h1 className="display-4">404 Not Found</h1>
          <p className="lead">The page you are looking for does not exist.</p>
          <button className="btn btn-primary" onClick={goHome}>Go Home</button>
        </div>
      </div>
    </div>
  );
}

export default NotFound;