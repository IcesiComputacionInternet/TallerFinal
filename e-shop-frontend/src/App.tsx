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

interface CartProduct {
  name: string;
  id: number;
  price: number;
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
  ];

  //Browser Router
  const router = Router(routes);

  return <RouterProvider router={router} />;
}

export default App;
