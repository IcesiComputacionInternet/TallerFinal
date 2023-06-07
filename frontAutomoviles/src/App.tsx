import { useState, useEffect } from 'react';
import { BrowserRouter, Navigate, Routes, Route } from 'react-router-dom';
import FooterInfo from './components/FooterInfo';

import AuthView from './views/Auth/AuthView';
import Home from './views/Home/Home';
import NotFound from './views/NotFound/NotFound';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem('jwt') !== null
  );

  useEffect(() => {
    localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);
  
  const logIn = () => {
    setIsLoggedIn(true);
  };

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route
            path="/login"
            element={<AuthView setLogin={logIn}/>}
          />
          <Route 
            path='/home' 
            element={isLoggedIn ? <Home /> : <Navigate to='/login' />} 
          />
          <Route 
            path='/*' 
            element={isLoggedIn ? <NotFound /> : <Navigate to='/login' />} 
          />
        </Routes>
      </BrowserRouter>

      <FooterInfo />
    </>
  )
}

export default App
