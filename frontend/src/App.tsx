import { useState, useEffect } from "react";
import LogIn from "./components/LogIn";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import SignUp from "./components/SignUp";
import NotFoundItem from "./components/NotFoundItem";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem("jwt") !== null
);

useEffect(() => {
  localStorage.setItem("logged_user", JSON.stringify(isLoggedIn));
}, [isLoggedIn]);

const logIn = () => setIsLoggedIn(true);

return (
  <BrowserRouter>
      <Routes>
          <Route path="/logIn" element={<LogIn setLogin={logIn} />}></Route>
          <Route
              path="/signUp" element={<SignUp />}
          ></Route>
          
      </Routes>
  </BrowserRouter>
);
}

export default App;