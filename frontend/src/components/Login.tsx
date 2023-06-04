import React, { useState } from "react";
import axios from "axios";
import { NavigateFunction, useNavigate } from "react-router-dom";

const baseUrl = "http://localhost:8080";

interface Props {
    setLogin: () => void;
}

const Login = ({ setLogin }: Props) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigation : NavigateFunction = useNavigate();

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        try {
            const { data } = await axios.post(
                baseUrl + "/token",
                {
                    username,
                    password,
                },
                {
                    headers: {
                        "Access-Control-Allow-Origin": baseUrl,
                    },
                }
            );
            if (data.token) {
                localStorage.setItem("jwt", data.token);
                localStorage.setItem("userEmail", username);
                setLogin();
                navigation("/");
            }
        } catch (error) {
            alert("Invalid username or password")
            navigation("/*");
        }

    };

    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className= "col-md-6">
                    <div className= "card text-center mb-3">
                        <div className="card-body">
                            <h3 className="card-title">Login</h3>
                            <form onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Username"
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
                                <button type="submit" className="btn btn-primary">
                                    Login
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;