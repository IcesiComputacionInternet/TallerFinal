import { useState } from "react";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import MainHome from "./MainHome";
import Login from "./Login";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(
    () => localStorage.getItem("jwt") !== null
  );

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleRegister = () => {
    setIsLoggedIn(false);
  };

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={<MainHome handleLogin={handleLogin} handleRegister={handleRegister} />}
        />
        <Route
          path="/login"
          element={
            isLoggedIn ? <Navigate to="/" /> : <Login setLogin={handleLogin} />
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;