import { useState, useEffect } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import MainHome from "./components/MainHome";
import Login from "./components/Login";
import Register from "./components/Register";
import NotFound from "./components/NotFound";
import HomeAdmin from "./components/HomeAdmin";

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
              <Route path="/*" element={<NotFound/>}>
              </Route>
              <Route path="/homeAdmin" element={<HomeAdmin/>}></Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;