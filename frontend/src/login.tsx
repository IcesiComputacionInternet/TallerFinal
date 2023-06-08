
import React, { useState } from "react";

import { NavigateFunction, useNavigate } from "react-router-dom";
import '../style/form.css'
import loginImg from '../assets/bookImageForLogin.svg'

const baseUrl = "http://localhost:8080";
interface Props {
    setLogin: () => void;
}
const Login = ({ setLogin }: Props) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigation : NavigateFunction = useNavigate();
   
    const pageForSignUp = () => {
        navigation("/signUp");
    }

    return (
        <body>
        <div className="container">
            <form className="login-form">
                <h3>Sign In</h3>
                <p>Welcome to BookStore</p>
                <div className="form-group">
                    <input required
                        type="text"
                        className="form-control"
                        placeholder="Provide Email or Phone"
                        value={username}
                        onChange={(event) => setUsername(event.target.value)}
                    />
                </div>
                <div className="form-group">
                    <input required
                        type="password"
                        className="form-control"
                        placeholder="your password"
                        value={password}
                        onChange={(event) => setPassword(event.target.value)}
                    />
                </div>
                <button className="btn btn-primary">
                    Log In
                    <div className="arrow-wrapper" >
                        <div className="arrow"></div>
                    </div>
                </button>
                <div className="splitter">
                    <span>Or</span>
                </div>
                <button onClick={pageForSignUp} className="btn btn-outline-primary">
                    Sign Up
                </button>
            </form>
            <img src={loginImg} alt="Mobile Login" className="svg-image" />
        </div>
        </body>
    );
};
export default Login;