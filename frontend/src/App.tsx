import { useState, useEffect } from "react";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import MainHome from "./components/MainHome";
import Login from "./components/Login";
import Register from "./components/Register";
import ShopHome from "./components/ShopHome";
import NotFound from "./components/NotFound";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem("jwt") !== null
  );

  useEffect(() => {
    localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);

  const logIn = () => setIsLoggedIn(true);

  const registered = () => true;

  return(
      <BrowserRouter>
          <Routes>
              <Route path="/login" element={<Login setLogin={logIn}/>}></Route>
              <Route
                  path="/"
                  element={<MainHome />}
              ></Route>
              <Route path="/register" element={<Register onRegistrationComplete={registered}/>}>
              </Route>
              <Route path="/home-shop" element={isLoggedIn ? <ShopHome /> : <Navigate to="/login" />} />
              <Route path="/*" element={<NotFound/>}>
              </Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;