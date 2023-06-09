import { useState, useEffect } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Login from "./components/Login";
import Register from "./components/Register";
import UsersShopHome from "./pages/users/ShopHome";
import NotFound from "./components/NotFound";
import HomeAdmin from "./pages/admin/AdminHome";
import AdminUsers from "./pages/admin/Users";
import CreateUsers from "./pages/admin/CreateUsers";
import Items from "./pages/admin-shop/Items";
import CreateRoles from "./pages/admin/CreateRole";
import AdminRoles from "./pages/admin/Roles";
import CreateItems from "./components/CreateItems";
import Orders from "./pages/admin-shop/Orders";
import CreateCategories from "./pages/admin/CreateCategories";
import AdminCategories from "./pages/admin/Categories";
import HomeShop from "./pages/shop/ShopHome";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem("jwt") !== null
  );

  useEffect(() => {
    localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);

  const logIn = () => setIsLoggedIn(true);

  const registered = () => true;

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login setLogin={logIn}/>}></Route>
        <Route
          path="/register"
          element={<Register onRegistrationComplete={registered} />}
        />
        <Route
          path="/users/home-shop"
          element={isLoggedIn ? <UsersShopHome /> : <Navigate to="/login" />}
        />
         <Route path="/*"
            element={isLoggedIn ? <NotFound />: <Navigate to = "/login"/>}
        ></Route>
        <Route path="/admin/home" element={<HomeAdmin />} />
        <Route path="/admin/users" element={<AdminUsers />} />
        <Route path="/items" element={<Items />} />
        <Route path="/admin/roles" element={<AdminRoles />} />
        <Route path="/admin/categories" element={<AdminCategories />} />
        <Route path="/admin/users/create" element={<CreateUsers />} />
        <Route path="/admin/roles/create" element={<CreateRoles />} />
        <Route path="/admin/categories/create" element={<CreateCategories />} />
        <Route path="/createitems" element={<CreateItems />} />
        <Route path="/shop/home" element={<HomeShop/>} />

        {isLoggedIn && (
          <Route path="/orders" element={<Orders />} />
        )}
      </Routes>
    </BrowserRouter>
  );
}

export default App;