import { useEffect } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";

function Logout (){

    const navigation : NavigateFunction = useNavigate();

    useEffect(() => {
      localStorage.setItem("logged_user", JSON.stringify(true));
    },);

    const handleClick = async (event: any) => {

        event.preventDefault();
        
        navigation("/login");

        localStorage.removeItem('jwt');
        localStorage.setItem("logged_user", JSON.stringify(false))
    };

    return (
        <>
          <button type="button" className="btn btn-outline-danger" onClick={handleClick}>Logout</button>
        </>
      );
      
}

export default Logout;