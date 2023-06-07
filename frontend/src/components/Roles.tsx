// @ts-ignore
import React, { useState } from "react";
// import axios from "axios";
// import { NavigateFunction, useNavigate } from "react-router-dom";
import '../style/form.css'

const baseUrl = "http://localhost:8080";


const Roles = () => {
    const [roleName, setRoleName] = useState("");
    const [roleDescription, setRoleDescription] = useState("");
    // const navigation : NavigateFunction = useNavigate();

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

    return (
        <body>
        <div className="container">
            <form className="login-form">
                <h3>New role</h3>
                <div className="form-group">
                    <input required
                           type="text"
                           className="form-control"
                           placeholder="Name"
                           value={roleName}
                           onChange={(event) => setRoleName(event.target.value)}
                    />
                </div>
                <div className="form-group">
                    <textarea required
                           className="form-control"
                           placeholder="Description"
                           value={roleDescription}
                           onChange={(event) => setRoleDescription(event.target.value)}
                    />
                </div>
                <div className="button-container">
                    <button className="btn btn-primary">Done</button>
                    <button className="btn btn-outline-danger" >Cancel</button>
                </div>
            </form>
        </div>
        </body>
    );
};



export default Roles;