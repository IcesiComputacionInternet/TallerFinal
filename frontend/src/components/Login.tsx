// @ts-ignore
import React, { useState } from "react";
// import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";
import '../style/form.css'
// import loginImg from '../assets/loginImg.svg'
import loginImg from '../assets/cellp.gif'

const baseUrl = "http://localhost:8080";

interface Props {
    setLogin: () => void;
}

const Login = ({ setLogin }: Props) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigation : NavigateFunction = useNavigate();

    // const handleSubmit = async (event: any) => {
    //     event.preventDefault();
    //
    //     try {
    //         const { data } = await axios.post(
    //             baseUrl + "/token",
    //             {
    //                 username,
    //                 password,
    //             },
    //             {
    //                 headers: {
    //                     "Access-Control-Allow-Origin": baseUrl,
    //                 },
    //             }
    //         );
    //         if (data.token) {
    //             localStorage.setItem("jwt", data.token);
    //             localStorage.setItem("userEmail", username);
    //             setLogin();
    //             navigation("/");
    //         }
    //     } catch (error) {
    //         alert("Invalid username or password")
    //         navigation("/*");
    //     }
    //
    // };

    const signUpPage = () => {
        navigation("/signUp");
    }

    return (
        <body>
        <div className="container">
            <form className="login-form">
                <h3>Sign in</h3>
                <p>Welcome to (nombre de la tienda)</p>
                <div className="form-group">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Email or Phone"
                        value={username}
                        onChange={(event) => setUsername(event.target.value)}
                    />
                </div>
                <div className="form-group">
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Password"
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

                <div className="divider">
                    <span>Or</span>
                </div>
                <button onClick={signUpPage} className="btn btn-outline-primary">
                    Sign up
                </button>
            </form>
            <img src={loginImg} alt="Mobile Login" className="svg-image" />
        </div>
        </body>
    );
};



export default Login;