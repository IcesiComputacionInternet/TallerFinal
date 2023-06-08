import "./App.css";
import {
  RouteObject,
  createBrowserRouter as Router,
  RouterProvider,
} from "react-router-dom";
import ErrorPage from "./pages/ErrorPage";
import Home from "./pages/Home";
import Login from "./components/Login";
import { useEffect, useState } from "react";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem("jwt") !== null
  );

  useEffect(() => {
    localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);

  const logIn = () => setIsLoggedIn(true);
  const routes: RouteObject[] = [
    {
      path: "/",
      element: <Home />,
      errorElement: <ErrorPage />,
    },
    {
      path: "/login",
      element: <Login setLogin={logIn} />,
    },
  ];

  //Browser Router
  const router = Router(routes);

  return <RouterProvider router={router} />;
}

export default App;
