import { useState, useEffect } from 'react';
import { BrowserRouter, Navigate, Routes, Route } from 'react-router-dom';
import FooterInfo from './components/FooterInfo';

import AuthView from './views/Auth/AuthView';
import Home from './views/Home/Home';
import NotFound from './views/NotFound/NotFound';
import Navbar from './components/Navbar';
import Toast from './components/Toast';
import Cart from './views/Cart/Cart';
import Orders from './views/Orders/Orders';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem('jwt') !== null
  );
  const [infoToast, setInfoToast] = useState({
    show: false,
    message: "",
    title: ""
  });

  useEffect(() => {
    localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);
  
  const logIn = () => {
    setIsLoggedIn(true);
  };

  const handleCloseToast = () => {
    setInfoToast({
        show: false,
        message: "",
        title: ""
    });
  };

  const handleOpenToast = (message: string, title: string) => {
    setInfoToast({
        show: true,
        message: message,
        title: title
    });
  };

  return (
    <>
      <Navbar isLoggedIn={isLoggedIn}/>
      <BrowserRouter>
        <Routes>
          <Route 
            path='/' 
            element={<Home isLogged={isLoggedIn} setInfoToast={handleOpenToast}/>} 
          />
          <Route
            path="/login"
            element={<AuthView setLogin={logIn} setInfoToast={handleOpenToast}/>}
          />
          <Route
            path="/cart"
            element={<Cart setInfoToast={handleOpenToast}/>}
          />
          <Route
            path="/orders"
            element={<Orders />}
          />
          <Route 
            path='/*' 
            element={isLoggedIn ? <NotFound /> : <Navigate to='/login' />} 
          />
        </Routes>
      </BrowserRouter>

      <FooterInfo />
      {infoToast.show && (
                <div className="toast-container position-fixed bottom-0 end-0 p-3">
                    <Toast title={infoToast.title} message={infoToast.message} onClose={handleCloseToast} />
                </div>
                )}
    </>
  )
}

export default App
