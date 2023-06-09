import "./App.css";
import {
  RouteObject,
  createBrowserRouter as Router,
  RouterProvider,
} from "react-router-dom";
import ErrorPage from "./pages/ErrorPage";
import Home from "./pages/Home";
import Login from "./components/Login";
import BuyPage from "./pages/BuyPage";
import { useEffect, useState } from "react";
import Order from "./pages/Order";
import Role from "./pages/Role";
import Items from "./pages/Items";
import Users from "./pages/Users";
import CreateItem from "./pages/CreateItem";
import ChangeStatusOrder from "./pages/ChangeStatusOrder";
import Category from "./pages/Category";

interface CartProduct {
  name: string;
  itemId: number;
  price: number;
  category: string;
  imageUrl: string;
  warranty: number;
  quantity: number;
}

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem("jwt") !== null
  );

  const [cart, setCart] = useState<CartProduct[]>([]);

  useEffect(() => {
    localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);

  const logIn = () => setIsLoggedIn(true);
  const routes: RouteObject[] = [
    {
      path: "/",
      element: <Home role="ADMIN" setCart={setCart} cart={cart} />,
      errorElement: <ErrorPage />,
    },
    {
      path: "/login",
      element: <Login setLogin={logIn} />,
    },
    {
      path: "/buy",
      element: <BuyPage cart={cart} setCart={setCart} />,
    },
    {
      path: "/orders",
      element: <Order />,
    },
    {
      path: "/roles",
      element: <Role />,
    },
    {
      path: "/items",
      element: <Items />,
    },
    {
      path: "/category",
      element: <Category />,
    },
    {
      path: "/changeStatusOrder",
      element: <ChangeStatusOrder />,
    },
    {
      path: "/createItem",
      element: <CreateItem />,
    },
    {
      path: "/users",
      element: <Users />,
    },
  ];

  //Browser Router
  const router = Router(routes);

  return <RouterProvider router={router} />;
}

export default App;
