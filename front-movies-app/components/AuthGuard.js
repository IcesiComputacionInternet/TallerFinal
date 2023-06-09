import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import {getCurrentUser} from '../util/methods'
import axios from 'axios'

const AuthGuard = ({ allowedRoles, children }) => {
  const router = useRouter();

  const [current, setCurrent] = useState({})

  const getCurrentUser = async () => {
    try {
        const {data} = await axios.get("http://localhost:8080/users/CurrentUser",
        {
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                "MediaType": "application/json",
                "Authorization": "Bearer " + localStorage.getItem('jwt')
            }
    
        })
  
        let res = {data}
        console.log("Sape")
        console.log(res)
        setCurrent(res)
        // setCurrent(res)
        // setIsLoadingCurrent(false)
  
      } catch (err) {
        console.error("Error fetching current user:", err);
      }
}



  useEffect(() => {
    getCurrentUser()
    const isAuthenticated = localStorage.getItem('jwt')

    if (!isAuthenticated) {
      router.push('/login'); // Redirige a la página de inicio de sesión si el usuario no está autenticado
    } else if (/*!allowedRoles.includes(current.role.name)*/1<2) {
      router.push('/acceso-denegado'); // Redirige a una página de acceso denegado si el usuario no tiene los permisos necesarios
    }
  }, []);

  return <>{children}</>;
};

export default AuthGuard;