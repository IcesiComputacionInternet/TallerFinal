// @ts-ignore
import React, { useState } from "react";
// import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
// import '../style/form.css'

const baseUrl = "http://localhost:8080";

interface Props {
    setLogin: () => void;
}

const Store = () => {
    // const [username, setUsername] = useState("");
    // const [password, setPassword] = useState("");
    const navigation : NavigateFunction = useNavigate();

    const signUpPage = () => {
        navigation("/signup");
    }

    // return (
    //
    // );
};



export default Store;