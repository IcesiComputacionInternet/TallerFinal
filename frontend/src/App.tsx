import { useState, useEffect } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import MainHome from "./components/MainHome";
import Login from "./components/Login";
import Register from "./components/Register";
import ShopHome from "./components/ShopHome";
import NotFound from "./components/NotFound";
import HomeAdmin from "./pages/admin/AdminHome";
import AdminUsers from "./pages/admin/Users";
import CreateUsers from "./pages/admin/CreateUsers";
import AdminItems from "./pages/admin/Items";
import AdminOrders from "./pages/admin/Orders";
import CreateRoles from "./pages/admin/CreateRole";
import AdminRoles from "./pages/admin/Roles";

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
              <Route path="/admin/users" element={<AdminUsers/>}></Route>
              <Route path="/admin/items" element={<AdminItems/>}></Route>
              <Route path="/admin/orders" element={<AdminOrders/>}></Route>
              <Route path="/admin/roles" element={<AdminRoles/>}></Route>
              <Route path="/admin/users/create" element={<CreateUsers/>}></Route>
              <Route path="/admin/roles/create" element={<CreateRoles/>}></Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;