import { useState, useEffect } from "react";
import Login from "./components/Login";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import SignUp from "./components/SignUp";
import CreateItem from "./components/CreateItem.tsx";
import Orders from "./components/Orders.tsx";
// import Store from "./components/Store.tsx";
// import NotFound from "./components/NotFound";

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
                <Route path="/" element={<Login setLogin={logIn} />}></Route>
                <Route path="/signup" element={<SignUp />}></Route>
                {/*<Route*/}
                {/*    path="/*"*/}
                {/*    element={isLoggedIn ? <NotFound /> : <Navigate to="/login" />}*/}
                {/*/>*/}
                {/*<Route path="/store" element={<Store />}></Route>*/}
                <Route path="/newitem" element={<CreateItem />}></Route>
                <Route path="/orders" element={<Orders />}></Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
