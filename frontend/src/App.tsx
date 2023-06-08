import { useState, useEffect } from "react";
import Login from "./pages/Login.tsx";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import SignUp from "./pages/SignUp.tsx";
import CreateItem from "./pages/CreateItem.tsx";
import Orders from "./pages/Orders.tsx";
import Roles from "./pages/Roles.tsx";
import { Store }  from "./pages/Store.tsx";
import { Navbar } from "./components/Navbar"
import {ShoppingCartProvider} from "./methods/ShoppingCartCtx.tsx";
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
        <ShoppingCartProvider>
                <BrowserRouter>
                    <Routes>
                        <Route path="/" element={<Login setLogin={logIn} />}></Route>
                        <Route path="/signup" element={<SignUp />}></Route>
                        {/*<Route*/}
                        {/*    path="/*"*/}
                        {/*    element={isLoggedIn ? <NotFound /> : <Navigate to="/login" />}*/}
                        {/*/>*/}
                        <Route path="/store" element={<Store />}></Route>
                        <Route path="/newitem" element={<CreateItem />}></Route>
                        <Route path="/orders" element={<Orders />}></Route>
                        <Route path="/roles" element={<Roles />}></Route>
                    </Routes>
                </BrowserRouter>
        </ShoppingCartProvider>

    );
}

export default App;
