import { useState, useEffect } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import MainHome from "./components/MainHome";
import Login from "./components/Login";
import Register from "./components/Register";
import ShopHome from "./components/ShopHome";
import NotFound from "./components/NotFound";
import HomeAdmin from "./pages/admin/AdminHome";
import Users from "./pages/admin/Users";
import Items from "./pages/admin/Items";
import Orders from "./pages/admin/Orders";

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
              <Route path="/admin/home" element={<HomeAdmin/>}></Route>
              <Route path="/admin/users" element={<Users/>}></Route>
              <Route path="/admin/items" element={<Items/>}></Route>
              <Route path="/admin/orders" element={<Orders/>}></Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;