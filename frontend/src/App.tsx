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
<<<<<<< HEAD
import AdminItems from "./pages/admin/Items";
import AdminOrders from "./pages/admin/Orders";
import CreateRoles from "./pages/admin/CreateRole";
import AdminRoles from "./pages/admin/Roles";
=======
import Items from "./pages/admin/Items";
import Orders from "./pages/admin/Orders";
import CreateItems from "./components/CreateItems";
>>>>>>> e4868fb53aef4eba905c69c78ff8453ef12fbd04

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
<<<<<<< HEAD
              <Route path="/admin/roles/create" element={<CreateRoles/>}></Route>
=======
              <Route path="/admin/items" element={<Items/>}></Route>
              <Route path="/admin/orders" element={<Orders/>}></Route>
              <Route path="/createitems" element={<CreateItems/>}></Route>
>>>>>>> e4868fb53aef4eba905c69c78ff8453ef12fbd04
          </Routes>
      </BrowserRouter>
  );
}

export default App;