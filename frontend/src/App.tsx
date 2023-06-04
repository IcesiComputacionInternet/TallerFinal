import { useState, useEffect } from "react";
import Login from "./components/Login";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import SignUp from "./components/SignUp";
import NotFound from "./components/NotFound";

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
                <Route path="/login" element={<Login setLogin={logIn} />}></Route>
                <Route
                    path="/signup" element={<SignUp />}
                ></Route>
                <Route
                    path="/*"
                    element={isLoggedIn ? <NotFound /> : <Navigate to="/login" />}
                />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
